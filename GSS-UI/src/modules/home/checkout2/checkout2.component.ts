import { CartService } from './../../../app/core/cart.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { State } from 'src/common/model/state';
import { Country } from 'src/common/model/country';
import { PaymentInfo } from 'src/common/model/payment-info';
import { Purchase } from 'src/common/model/purchase';
import { CheckoutService } from 'src/common/service/checkout.service';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';
import { Order } from 'src/common/model/order';
import { OrderItem } from 'src/common/model/order-item';
import { CartItem } from 'src/common/model/cartItem';

@Component({
  selector: 'app-checkout2',
  templateUrl: './checkout2.component.html',
  styleUrls: ['./checkout2.component.css']
})
export class Checkout2Component implements OnInit {
  // initialize Stripe API
  // checkoutForm!: FormGroup;
  stripe = Stripe(environment.stripePublishableKey);

  paymentInfo: PaymentInfo = new PaymentInfo();
  cardElement: any;
  displayError: any = "";
  checkoutFormGroup: FormGroup;
  isDisabled: boolean = false;
  totalPrice: number = 0;
  totalQuantity: number = 0;
  cartItems: CartItem[] = [];

  constructor(private fb: FormBuilder,
    private checkoutService: CheckoutService,
    private router: Router,
    private cartService : CartService,
  ) { }

  ngOnInit() {
    this.reviewCartDetails();
    this.setupStripePaymentForm();
    this.checkoutFormGroup = this.fb.group({
          customer: this.fb.group({
            firstName: ['', [Validators.required, Validators.minLength(3)]],
            lastName: ['', [Validators.required, Validators.minLength(3)]],
            // name: ['', [Validators.required, Validators.minLength(3)]],
            email: ['', [Validators.required, Validators.email]],
            address: ['', Validators.required],
            // phone: [
            //   '',
            //   [Validators.required, Validators.pattern('^[0-9]{10,15}$')],
            // ],
          }),
          billingAddress: this.fb.group({
            street: ['', [Validators.required, Validators.minLength(2)]],
            city: ['', [Validators.required, Validators.minLength(2)]],
            state: ['', [Validators.required]],
            country: ['', [Validators.required]],
            zipCode: ['', [Validators.required, Validators.minLength(2)]],
          }),
          shippingAddress: this.fb.group({
            street: ['', [Validators.required, Validators.minLength(2)]],
            city: ['', [Validators.required, Validators.minLength(2)]],
            state: ['', [Validators.required]],
            country: ['', [Validators.required]],
            zipCode: ['', [Validators.required, Validators.minLength(2)]],
          }),

    }
    )
  }

  reviewCartDetails() {

    this.cartService.cart$.subscribe(items => {
      this.cartItems = items;
    });

    // subscribe to cartService.totalQuantity
    this.cartService.totalQuantity.subscribe(
      totalQuantity => this.totalQuantity = totalQuantity
    );

    // subscribe to cartService.totalPrice
    this.cartService.totalPrice.subscribe(
      totalPrice => this.totalPrice = totalPrice
    );

    this.cartService.computeCartTotals();

  }


  copyShippingAddressToBillingAddress(event : any) {

    if (event.target.checked) {
      this.checkoutFormGroup.controls['shippingAddress']
            .setValue(this.checkoutFormGroup.controls['billingAddress'].value);

      // bug fix for states
      // this.billingAddressStates = this.shippingAddressStates;

    }
    else {
      this.checkoutFormGroup.controls['shippingAddress'].reset();

      // bug fix for states
      // this.billingAddressStates = [];
    }

  }

  setupStripePaymentForm() {

    // get a handle to stripe elements
    var elements = this.stripe.elements();

    // Create a card element ... and hide the zip-code field
    this.cardElement = elements.create('card');
    // this.cardElement = elements.create('card', { hidePostalCode: true });

    // Add an instance of card UI component into the 'card-element' div
    this.cardElement.mount('#card-element');

    // Add event binding for the 'change' event on the card element
    this.cardElement.on('change', (event:any) => {

      // get a handle to card-errors element
      this.displayError = document.getElementById('card-errors');

      if (event.complete) {
        this.displayError.textContent = "";
      } else if (event.error) {
        // show validation error to customer
        this.displayError.textContent = event.error.message;
      }

    });

  }

  onSubmit() {
    console.log("Handling the submit button");

    if (this.checkoutFormGroup.invalid) {
      this.checkoutFormGroup.markAllAsTouched();
      console.log("form invalid");
      return;
    }

    // set up order
    let order = new Order();
    order.totalPrice = this.totalPrice;
    order.totalQuantity = this.totalQuantity;


    // order.totalPrice = 200;
    // order.totalQuantity = 3;
    // get cart items
    const cartItems = this.cartService.cartItems;

    // create orderItems from cartItems
    // - long way
    /*
    let orderItems: OrderItem[] = [];
    for (let i=0; i < cartItems.length; i++) {
      orderItems[i] = new OrderItem(cartItems[i]);
    }
    */

    // - short way of doing the same thingy
    let orderItems: OrderItem[] = cartItems.map(tempCartItem => new OrderItem(tempCartItem));

    // set up purchase
    let purchase = new Purchase();

    // populate purchase - customer
    purchase.customer = this.checkoutFormGroup.controls['customer'].value;

    // populate purchase - shipping address
    purchase.shippingAddress = this.checkoutFormGroup.controls['shippingAddress'].value;
    const shippingState: State = JSON.parse(JSON.stringify(purchase.shippingAddress.state));
    const shippingCountry: Country = JSON.parse(JSON.stringify(purchase.shippingAddress.country));
    purchase.shippingAddress.state = shippingState.name ?? "Dakak";
    purchase.shippingAddress.country = shippingCountry.name ?? "Vietnam";

    // populate purchase - billing address
    purchase.billingAddress = this.checkoutFormGroup.controls['billingAddress'].value;
    const billingState: State = JSON.parse(JSON.stringify(purchase.billingAddress.state));
    const billingCountry: Country = JSON.parse(JSON.stringify(purchase.billingAddress.country));
    purchase.billingAddress.state = billingState.name ?? "Dakak";
    purchase.billingAddress.country = billingCountry.name ?? "Vietnam";

    // populate purchase - order and orderItems
    purchase.order = order;
    purchase.orderItems = orderItems;

    // compute payment info
    this.paymentInfo.amount = Math.round(this.totalPrice * 100);
    this.paymentInfo.currency = "USD";
    this.paymentInfo.receiptEmail = purchase.customer.email;

    // if valid form then
    // - create payment intent
    // - confirm card payment
    // - place order

    if (!this.checkoutFormGroup.invalid && this.displayError.textContent === "") {

      this.isDisabled = true;

      this.checkoutService.createPaymentIntent(this.paymentInfo).subscribe(
        (paymentIntentResponse) => {
          this.stripe.confirmCardPayment(paymentIntentResponse.client_secret,
            {
              payment_method: {
                card: this.cardElement,
                billing_details: {
                  email: purchase.customer.email,
                  name: `${purchase.customer.firstName} ${purchase.customer.lastName}`,
                  address: {
                    line1: purchase.billingAddress.street,
                    city: purchase.billingAddress.city,
                    state: purchase.billingAddress.state,
                    postal_code: purchase.billingAddress.zipCode,
                    // country: this.billingAddressCountry.value.code
                  }
                }
              }
            }, { handleActions: false })
          .then((result: any) => {
            if (result.error) {
              // inform the customer there was an error
              alert(`There was an error: ${result.error.message}`);
              this.isDisabled = false;
            } else {
              // call REST API via the CheckoutService
              this.checkoutService.placeOrder(purchase).subscribe({
                next: response => {
                  alert(`Your order has been received.\nOrder tracking number: ${response.orderTrackingNumber}`);

                  // reset cart
                  this.resetCart();
                  this.isDisabled = false;
                },
                error: err => {
                  alert(`There was an error: ${err.message}`);
                  this.isDisabled = false;
                }
              })
            }
          });
        }
      );
    } else {
      this.checkoutFormGroup.markAllAsTouched();
      return;
    }

  }


  resetCart() {
    // reset cart data
    // this.cartService.cartItems = [];
    // this.cartService.totalPrice.next(0);
    // this.cartService.totalQuantity.next(0);
    // this.cartService.persistCartItems();

    // reset the form
    this.checkoutFormGroup.reset();

    // navigate back to the products page
    this.router.navigateByUrl("/home");
  }

}
