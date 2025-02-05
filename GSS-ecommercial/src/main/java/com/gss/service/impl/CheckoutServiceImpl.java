package com.gss.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gss.service.CheckoutService;
import com.gss.service.CustomerService;
import com.gss.service.OrderService;
//import com.luv2code.ecommerce.dao.CustomerRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import dto.PaymentInfo;
import dto.Purchase;
import dto.PurchaseResponse;
import entities.Customer;
import entities.Order;
import entities.OrderItem;
import lombok.Data;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	@Autowired
    private CustomerService customerService;
	
	@Autowired
    private OrderService orderService;

    public CheckoutServiceImpl(
//    		CustomerRepository customerRepository,
                               @Value("${stripe.key.secret}") String secretKey) {

//        this.customerRepository = customerRepository;

        // initialize Stripe API with secret key
        Stripe.apiKey = secretKey;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();

        // check if this is an existing customer
        String theEmail = customer.getEmail();
        
        Customer customerFromDB = customerService.findByEmail(theEmail);

        if (customerFromDB != null) {
//             we found them ... let's assign them accordingly
            customer = customerFromDB;
        }

        customer.addOrder(order);
//        order.setId("6773621862f58263370757f2");

        // save to the database
//        customerService.saveCustomer(customer);
//        Map<String, Object> dataToUpdate = new HashMap<String, Object>();
//        dataToUpdate.put("_id", order);
        
//        customerService.updateCustomer(customer.getId(),dataToUpdate);
        customerService.saveCustomer(customer);
        order.setDateCreated(LocalDateTime.now());
        orderService.saveDocument(order);
        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {

        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
//        params.put("amount", paymentInfo.getAmount());
        params.put("amount", 100);
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "Luv2Shop purchase");
        params.put("receipt_email", paymentInfo.getReceiptEmail());

        return PaymentIntent.create(params);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
        //
        return UUID.randomUUID().toString();
    }
}









