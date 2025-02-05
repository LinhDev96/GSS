package entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Address {
//	@Id
//	private Long id;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;

//    private Order order;
}
