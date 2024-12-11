package entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
public class category {
	@Id
	String id;
	
	String code;
	String name;
}
