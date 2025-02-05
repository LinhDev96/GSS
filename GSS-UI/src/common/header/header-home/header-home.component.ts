import { CartService } from './../../../app/core/cart.service';
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
  itemNum: number = 0;
  constructor(
    private router: Router,
    public dialog: MatDialog,
    private apiService: CoreService,
    private cartService: CartService
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
    this.cartService.cart$.subscribe(items => {
      this.itemNum = items.length;
    });
  }

  goHome() {
    this.router.navigate(['/home']);
  }

  buttonClick(){
    const dialogRef = this.dialog.open(Login2DialogComponent, {
    });
  }


}
