package dto;

import java.util.Set;

import entities.Address;
import entities.Customer;
import entities.Order;
import entities.OrderItem;
import lombok.Data;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
