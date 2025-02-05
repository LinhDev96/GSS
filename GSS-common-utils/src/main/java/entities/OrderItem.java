package entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
	@Id
    private Long id;

    private String imageUrl;

    private BigDecimal unitPrice;

    private int quantity;

    private String productId;

//    private Order order;
}
