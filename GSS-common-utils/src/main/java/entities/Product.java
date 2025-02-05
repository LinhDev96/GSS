package entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document(collection = "products")
public class Product {
	@Id
	private String id;
	private String sku;
	private String category;
	private String name;
	private String desc;
	private Double unitPrice;
	private String imageUrl;
	private String active;
	private LocalDateTime updatedAt;
	private LocalDateTime  createdAt;
	
//	public Product() {
//		this.desc = "this is test text";
//	};
	
//	public Product(String[] seq) {
//		this.sku = seq[0];
//		this.name = seq[1];
//		this.desc = seq[2];
//		this.unitPrice = seq[3];
//		this.imageUrl = seq[4];
//		this.active = seq[5];
//		this.updatedAt = seq[6];
//		this.createdAt = LocalDateTime.now();
//	};
}
