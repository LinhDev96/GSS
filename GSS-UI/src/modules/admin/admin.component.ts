import { CoreService } from '../../common/service/core.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { ConfirmDialogComponent } from '../../app/dialog/confirm/confirm-dialog/confirm-dialog.component';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  data :any;
  displayedColumns: string[] = [
    'checkbox',
    'id',
    'sku',
    'name',
    'description',
    'unitPrice',
    'imgUrl',
    'active',
  ];
  dataSource: any;
  productForm: FormGroup;
  checkedResources: string[] = [];

  constructor(
    public dialog: MatDialog,
    private apiService : CoreService,
    private fb: FormBuilder) {
    this.productForm = this.fb.group({
      id: [''],
      sku: ['', Validators.required],
      name: [''],
      desc: [''],
      unitPrice: [''],
      imageUrl: [''],
      active: ['']
    });
  }
  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts(){
    this.apiService.getData().subscribe({
      next: (response) => {
        this.data = response;
        this.dataSource = response;
        console.log(this.data);
      },
      error: (error) => {
        console.error('Có lỗi xảy ra!  ', error);
      },
      complete: () => {
        console.log('Hoàn thành!');
      }
    })
  }

  onSubmit(): void {
    console.log(this.productForm.value);
    if (this.productForm.valid) {
      // Process the form data
      console.log('Form Submitted!', this.productForm.value);
      this.openDialog();
      // Add your form processing logic here (e.g., send the data to a server)
    } else {
      // Mark all controls as touched to trigger validation messages
      this.productForm.markAllAsTouched();
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent);

    dialogRef.afterClosed().subscribe((result: any) => {
      if (result) {
        console.log('Yes clicked');
        this.insertProduct();
      } else {
        console.log('No clicked');
      }
    });
  }

  insertProduct(){
    this.apiService.postData(this.productForm.value).subscribe((data) => {
      this.getAllProducts();
    });
  }

  onCheckboxChange(event: any) {
    if (event.checked) {
      this.deleteElement(event.id);
    }
  }
  deleteChecked() {
    this.apiService.deleteMultipleProduct(this.checkedResources).subscribe(response => {
      console.log("already deleted",response);
      this.getAllProducts();
    });;
  }

  deleteElement(id: string) {


  }


  onCheckBoxCheck(event: MatCheckboxChange, elemnentId: string): void {
    if (event.checked) {
      if (elemnentId === 'all'){
      }else {
        if (!this.checkedResources.includes(elemnentId))
          this.checkedResources.push(elemnentId);
      }
    } else {
      if (elemnentId === 'all') this.checkedResources.length = 0;
      else {
        for (let i = 0; i < this.checkedResources.length; i++) {
          if (this.checkedResources[i] === elemnentId)
            this.checkedResources.splice(i, 1);
        }
      }
    }
  }

}
