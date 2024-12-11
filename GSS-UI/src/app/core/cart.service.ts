import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { CartItem } from '../../common/model/cartItem';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItems: CartItem[] = [];
  private cartSubject = new BehaviorSubject<CartItem[]>([]);
  cart$ = this.cartSubject.asObservable();

  constructor() { }

  addToCart(item: CartItem) {
    const index = this.cartItems.findIndex(cartItem => cartItem.id === item.id);

    if (index === -1) {
      this.cartItems.push(item);
    } else {
      this.cartItems[index].quantity += item.quantity;
    }

    this.cartSubject.next([...this.cartItems]); // Update BehaviorSubject
  }

  // Method to remove or reduce quantity of a product in the cart
  removeFromCart(itemId: string, quantity: number) {
    const index = this.cartItems.findIndex(cartItem => cartItem.id === itemId);

    if (index !== -1) {
      if (this.cartItems[index].quantity > quantity) {
        this.cartItems[index].quantity -= quantity;
      } else {
        this.cartItems.splice(index, 1); // Remove the item if quantity becomes zero
      }

      this.cartSubject.next([...this.cartItems]); // Update BehaviorSubject
    }
  }

  // Method to clear the cart
  clearCart() {
    this.cartItems = [];
    this.cartSubject.next([...this.cartItems]); // Update BehaviorSubject
  }
}
