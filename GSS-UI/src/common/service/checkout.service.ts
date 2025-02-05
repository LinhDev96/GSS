import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Purchase } from '../model/purchase';
import { PaymentInfo } from '../model/payment-info';

@Injectable({
  providedIn: 'root',
})
export class CheckoutService {
  private purchaseUrl = environment.GssApiUrl + 'checkout/purchase';

  private paymentIntentUrl =
    environment.GssApiUrl + 'checkout/payment-intent';

  constructor(private httpClient: HttpClient) {}

  placeOrder(purchase: Purchase): Observable<any> {
    return this.httpClient.post<Purchase>(this.purchaseUrl, purchase);
  }

  createPaymentIntent(paymentInfo: PaymentInfo): Observable<any> {
    return this.httpClient.post<PaymentInfo>(
      this.paymentIntentUrl,
      paymentInfo
    );
  }
}
