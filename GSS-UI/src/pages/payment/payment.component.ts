import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {
  paymentForm!: FormGroup;
  months: { value: number; viewValue: string }[] = [];
  years: number[] = [];

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.paymentForm = this.fb.group({
      cardType: ['', [Validators.required]],
      nameOnCard: ['',
        [
          Validators.required,
          Validators.pattern("^[a-zA-Z0-9 .'-]+$"), // Chỉ cho phép chữ, số, khoảng trắng, dấu nháy và dấu gạch ngang
          Validators.minLength(2),
          Validators.maxLength(50),
        ]
      ],
      cardNumber: ['',
        [
          Validators.required,
          Validators.maxLength(19),
          Validators.pattern('^[0-9]*$')
        ]],
      securityCode: ['',[Validators.required, Validators.pattern('^[0-9]{3,4}$')]],
      expirationMonth: ['', Validators.required],
      expirationYear: ['', Validators.required],
    });

    this.populateMonths();
    this.populateYears();
  }

  populateMonths() {
    for (let i = 1; i <= 12; i++) {
      this.months.push({ value: i, viewValue: i.toString().padStart(2, '0') });
    }
  }

  populateYears() {
    const currentYear = new Date().getFullYear();
    for (let year = 1970; year <= currentYear; year++) {
      this.years.push(year);
    }
  }

  onSubmit() {
    console.log(this.paymentForm.value);
  }
}
