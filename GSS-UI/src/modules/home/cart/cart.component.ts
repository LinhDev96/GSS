import { Component, OnInit } from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { CartItem } from '../../../common/model/cartItem';
import { Router } from '@angular/router';
import { CartService } from '../../../app/core/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  dataSource: any =[];
  displayedColumns: string[] = [
    'product',
    'unitPrice',
    'quantity',
    'totalPrice',
  ];
  cartItems: CartItem[] = [];
  summaryTotalPrice: number = 0;

  constructor(
    private cartService: CartService,
    private router: Router
  ) {}

  ngOnInit() {
    // Subscribe to the cart observable
    this.cartService.cart$.subscribe(items => {
      this.cartItems = items;
    });
    this.dataSource = this.cartItems;
  }

  // Method to increase the quantity of a cart item
  increaseQuantity(item: CartItem) {
    this.cartService.addToCart({ ...item, quantity: 1 });
    this.totalPricePerItems(item);
  }

  // Method to decrease the quantity of a cart item
  decreaseQuantity(item: CartItem) {
    this.cartService.removeFromCart(item.id, 1);
    this.totalPricePerItems(item);
  }

  // Method to clear the cart
  clearCart() {
    this.cartService.clearCart();
  }
  totalPricePerItems(item: CartItem): number {
    item.totalPrice = item.unitPrice * item.quantity;
    return item.totalPrice;
  }

  sumaryTotalPrice(): number {
    this.summaryTotalPrice = 0;
    this.cartItems.forEach(item => {
      this.summaryTotalPrice += item.totalPrice;
    });
    return this.summaryTotalPrice;
  }
  goToCheckout() {
    this.router.navigate(['/home/cart/checkout']);
  }
  onCheckBoxCheck(event: MatCheckboxChange, elemnentId: string): void {}

}