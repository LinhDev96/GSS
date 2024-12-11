import { Component, OnInit } from '@angular/core';
import { CoreService } from '../../service/core.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { Login2DialogComponent } from '../../../app/dialog/login/login2-dialog.component/login2-dialog/login2-dialog.component';

@Component({
  selector: 'app-header-home',
  templateUrl: './header-home.component.html',
  styleUrls: ['./header-home.component.css']
})
export class HeaderHomeComponent implements OnInit {
  data: any;
  constructor(
    private router: Router,
    public dialog: MatDialog,
    private apiService: CoreService
  ) { }

  ngOnInit() {
    this.apiService.getData().subscribe({
      next: (response) => {
        this.data = response;
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

  goHome() {
    this.router.navigate(['/home']);
  }

  buttonClick(){
    const dialogRef = this.dialog.open(Login2DialogComponent, {
    });
  }


}
