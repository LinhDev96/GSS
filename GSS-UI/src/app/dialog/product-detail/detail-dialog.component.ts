import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CommonService } from '../../../common/service/common.service';
import { CartItem } from '../../../common/model/cartItem';
import { CartService } from '../../core/cart.service';

@Component({
  selector: 'app-detail-dialog',
  templateUrl: './detail-dialog.component.html',
  styleUrls: ['./detail-dialog.component.css'],
})
export class DetailDialogComponent implements OnInit {
  itemId: any;
  items: CartItem[] = [];

  item: CartItem = {
    id: '',
    productName: '',
    unitPrice: 0,
    quantity: 0,
    totalPrice: 0,
  };
  imgUrls = [];
  productName: string = '';
  unitPrice: number = 0;
  quantity: number = 0;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<DetailDialogComponent>,
    private commonService: CommonService,
    private cartService: CartService
  ) {}

  ngOnInit() {
    this.imgUrls = this.commonService?.imgUrls;
    this.itemId = this.data.itemId;
    this.productName = this.data.productName;
    this.unitPrice = this.data.unitPrice;
    this.quantity = this.data.quantity;
    console.log(this.itemId);
  }

  closeDetailDialog() {
    this.dialogRef.close();
  }


  addToCart() {
    const newItem: CartItem = {
      id: this.itemId, // Tạo ID ngẫu nhiên hoặc sử dụng ID có sẵn
      productName: this.productName,
      unitPrice: this.unitPrice,
      quantity: 1,
      totalPrice: this.unitPrice,
    };

    this.cartService.addToCart(newItem); // Thêm item vào giỏ hàng qua CartService
    this.dialogRef.close(); // Đóng dialog
  }
}
