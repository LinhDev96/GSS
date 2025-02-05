package entities;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "customers")
public class Customer {
	 	@Id
	    private String id;

	    private String firstName;

	    private String lastName;

	    private String email;

//		@DBRef
	    private Set<Order> orders = new HashSet<>();

	    public void addOrder(Order order) {

	        if (order != null) {

	            if (orders == null) {
	                orders = new HashSet<>();
	            }

	            orders.add(order);
//	            order.setCustomer(this);
	        }
	    }
}
