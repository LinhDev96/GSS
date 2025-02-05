package entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Document(collection = "orders")
public class Order {
	@Id
    private String id;

    private String orderTrackingNumber;

    private int totalQuantity;

    private BigDecimal totalPrice;

    private String status;

    private LocalDateTime dateCreated;

    private LocalDateTime lastUpdated;

    private Set<OrderItem> orderItems = new HashSet<>();
    
//    @JsonIgnore
//    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    public void add(OrderItem item) {

        if (item != null) {
            if (orderItems == null) {
                orderItems = new HashSet<>();
            }

            orderItems.add(item);
//            item.setOrder(this);
        }
    }
}
