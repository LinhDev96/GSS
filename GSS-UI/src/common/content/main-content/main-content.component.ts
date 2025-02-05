import { Component, OnInit } from '@angular/core';
import { CoreService } from '../../service/core.service';
import { MatDialog } from '@angular/material/dialog';
import { DetailDialogComponent } from '../../../app/dialog/product-detail/detail-dialog.component';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.css']
})
export class MainContentComponent implements OnInit {
  items :any;
  total = 0;
  page = 0;
  pageSizeOptions: number[] = [10, 25, 100];
  constructor(
    public dialog: MatDialog,
    private apiService : CoreService,
  ) { }

  ngOnInit() {
    // this.getAllProducts();
  }
  getAllProducts(){
    this.apiService.getData().subscribe({
      next: (response: any) => {
        this.items = response;
        this.total = this.items.length;
      },
      error: (error: any) => {
        console.error('Có lỗi xảy ra!  ', error);
      },
      complete: () => {
        console.log('Hoàn thành!');
      }
    })
  }

  navigate(event: any) {
    this.page = event.pageIndex;
  }

  openDetailDialog(){
    const dialogRef = this.dialog.open(DetailDialogComponent, {
    });
  }

}
