import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Country } from '../../../common/model/country';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.scss',
})
export class CheckoutComponent implements OnInit {
  checkoutForm!: FormGroup;
  billingAddressStates: any[] = [];
  shippingAddressStates: any[] = [];
  totalQuantity: number = 0;
  totalPrice: number = 0;
  countries: Country[] = [];

  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit() {
    this.checkoutForm = this.fb.group({
      customer: this.fb.group({
        firstName: ['', [Validators.required, Validators.minLength(3)]],
        lastName: ['', [Validators.required, Validators.minLength(3)]],
        name: ['', [Validators.required, Validators.minLength(3)]],
        email: ['', [Validators.required, Validators.email]],
        address: ['', Validators.required],
        phone: [
          '',
          [Validators.required, Validators.pattern('^[0-9]{10,15}$')],
        ],
      }),
      shippingAddress: this.fb.group({
        street: ['', [Validators.required, Validators.minLength(2)]],
        city: ['', [Validators.required, Validators.minLength(2)]],
        state: ['', [Validators.required]],
        country: ['', [Validators.required]],
        zipCode: ['', [Validators.required, Validators.minLength(2)]],
      }),
      billingAddress: this.fb.group({
        street: ['', [Validators.required, Validators.minLength(2)]],
        city: ['', [Validators.required, Validators.minLength(2)]],
        state: ['', [Validators.required]],
        country: ['', [Validators.required]],
        zipCode: ['', [Validators.required, Validators.minLength(2)]],
      }),
      creditCard: this.fb.group({
        cardType: ['', [Validators.required]],
        nameOnCard: ['', [Validators.required, Validators.minLength(2)]],
        cardNumber: [
          '',
          [Validators.required, Validators.pattern('[0-9]{16}')],
        ],
        securityCode: [
          '',
          [Validators.required, Validators.pattern('[0-9]{3}')],
        ],
        expirationMonth: [''],
        expirationYear: [''],
      }),
    });
  }


  copyShippingAddressToBillingAddress(event : any) {

    if (event.target.checked) {
      this.checkoutForm.controls['billingAddress']
            .setValue(this.checkoutForm.controls['shippingAddress'].value);

      // bug fix for states
      this.billingAddressStates = this.shippingAddressStates;

    }
    else {
      this.checkoutForm.controls['billingAddress'].reset();

      // bug fix for states
      this.billingAddressStates = [];
    }

  }

  getStates(formGroupName: string) {

    const formGroup = this.checkoutForm.get(formGroupName);

    if (formGroup) {
      const countryCode = formGroup.value.country.code;
      const countryName = formGroup.value.country.name;

      console.log(`${formGroupName} country code: ${countryCode}`);
      console.log(`${formGroupName} country name: ${countryName}`);
    }

    // console.log(`${formGroupName} country code: ${countryCode}`);
    // console.log(`${formGroupName} country name: ${countryName}`);

    // this.luv2ShopFormService.getStates(countryCode).subscribe(
    //   data => {

    //     if (formGroupName === 'shippingAddress') {
    //       this.shippingAddressStates = data;
    //     }
    //     else {
    //       this.billingAddressStates = data;
    //     }

    //     // select first item by default
    //     formGroup.get('state').setValue(data[0]);
    //   }
    // );
  }

  onSubmit() {
    if (this.checkoutForm.valid) {
      console.log('Form Submitted', this.checkoutForm.value);
      // Logic xử lý checkout ở đây, ví dụ gửi dữ liệu tới backend
      this.router.navigate(['/home/cart/payment']);
    } else {
      console.log('Form Invalid');
      this.checkoutForm.markAllAsTouched();
    }
  }
}
