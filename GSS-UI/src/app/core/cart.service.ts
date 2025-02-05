import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { CartItem } from '../../common/model/cartItem';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  totalPrice: Subject<number> = new BehaviorSubject<number>(0);
  totalQuantity: Subject<number> = new BehaviorSubject<number>(0);
  cartItems: CartItem[] = [];
  private cartSubject = new BehaviorSubject<CartItem[]>(this.getCartFromSessionStorage());
  cart$ = this.cartSubject.asObservable();

  constructor() { }

  private getCartFromSessionStorage(): CartItem[] {
    const cart = sessionStorage.getItem('cart');
    return cart ? JSON.parse(cart) : [];
  }

  private saveCartToSessionStorage(cart: CartItem[]): void {
    sessionStorage.setItem('cart', JSON.stringify(cart));
  }

  addToCart(item: CartItem) {
    const index = this.cartItems.findIndex(cartItem => cartItem.id === item.id);

    if (index === -1) {
      this.cartItems.push(item);
    } else {
      this.cartItems[index].quantity += item.quantity;
    }

    this.cartSubject.next([...this.cartItems]); // Update BehaviorSubject
    this.saveCartToSessionStorage([...this.cartItems]);
  }

  // Method to remove or reduce quantity of a product in the cart
  removeFromCart(itemId: string, quantity: number) {
    const index = this.cartItems.findIndex(cartItem => cartItem.id === itemId);

    if (index !== -1) {
      if (this.cartItems[index].quantity > quantity) {
        this.cartItems[index].quantity -= quantity;
      } else {
        // this.cartItems.splice(index, 1); // Remove the item if quantity becomes zero
      }

      this.cartSubject.next([...this.cartItems]); // Update BehaviorSubject
      this.saveCartToSessionStorage([...this.cartItems]);
    }
  }

  computeCartTotals() {

    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for (let currentCartItem of this.cartItems) {
      totalPriceValue += currentCartItem.quantity * currentCartItem.unitPrice;
      totalQuantityValue += currentCartItem.quantity;
    }

    // publish the new values ... all subscribers will receive the new data
    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);

    // log cart data just for debugging purposes
    // this.logCartData(totalPriceValue, totalQuantityValue);

    // persist cart data
    // this.persistCartItems();
  }

  // Method to clear the cart
  clearCart() {
    this.cartItems = [];
    this.cartSubject.next([...this.cartItems]); // Update BehaviorSubject
    this.saveCartToSessionStorage([...this.cartItems]);
  }

  removeItemFromCart(itemId: string){
    const index = this.cartItems.findIndex(cartItem => cartItem.id === itemId);
    this.cartItems.splice(index, 1); // Remove the item if quantity becomes zero
    this.cartSubject.next([...this.cartItems]);
    this.saveCartToSessionStorage([...this.cartItems]);
  }
}
