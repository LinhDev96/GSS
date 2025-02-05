package com.gss.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import dto.PaymentInfo;
import dto.Purchase;
import dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;

}
