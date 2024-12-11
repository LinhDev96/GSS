import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.scss'
})
export class CheckoutComponent implements OnInit {
  checkoutForm!: FormGroup;

  constructor(private fb: FormBuilder) {

  }

  ngOnInit() {
    this.checkoutForm = this.fb.group({
      customer: this.fb.group({
        name: ['', [Validators.required, Validators.minLength(3)]],
        email: ['', [Validators.required, Validators.email]],
        address: ['', Validators.required],
        phone: ['', [Validators.required, Validators.pattern('^[0-9]{10,15}$')]],
      })

    });
  }


  onSubmit() {
    if (this.checkoutForm.valid) {
      console.log('Form Submitted', this.checkoutForm.value);
      // Logic xử lý checkout ở đây, ví dụ gửi dữ liệu tới backend
    } else {
      console.log('Form Invalid');
    }
  }
}
