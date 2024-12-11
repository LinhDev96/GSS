package com.gss.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.gss.service.ProductService;

import entities.Product;
import lombok.Data;
import utils.MongoUtils;

@Service
@Data
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private MongoUtils mongoUtils;
//	MongoUtils mongoUtils;

	@Override
	public List<Product> getAllProducts() throws Exception {
		// TODO Auto-generated method stub
		List<Product> result = mongoUtils.findAll(Product.class);
		return result;
	}

	@Override
	public void createProducts(Map<String, Object> dataToSave) {
		String sku = (String) dataToSave.get("sku");
		String name = (String) dataToSave.get("name");
		String desc = (String) dataToSave.get("desc");
		Double unitPrice = Double.parseDouble((String)dataToSave.get("unitPrice"));
		String imageUrl = (String) dataToSave.get("imageUrl");
		String active = (String) dataToSave.get("active");
//		String updatedAt = (String) dataToSave.get("updatedAt");
//		String createdAt = (String) dataToSave.get("createdAt");
//		String[] seq = {sku,name,desc,unitPrice,imageUrl,active};
		
		Product product = new Product();
		product.setName(name);
		product.setSku(sku);
		product.setDesc(desc);
		product.setUnitPrice(unitPrice);
		product.setImageUrl(imageUrl);
		product.setActive(active);
		product.setUpdatedAt(LocalDateTime.now());
		product.setCreatedAt(LocalDateTime.now());
		
		mongoUtils.saveDocument(product);
	}
	@Override
	public void deleteProduct(String id) throws Exception {
		mongoUtils.deleteDocument(id,Product.class);
	}
	
	@Override
    public Page<Product> getProductByParams(Pageable pageable,Map<String,String> params) {
		params.values().removeIf(ObjectUtils::isEmpty);
		List<Criteria> criteriaList = new ArrayList<>();

        if (params.get("employeeID") != null && !params.get("employeeID").isEmpty()) {
            criteriaList.add(Criteria.where("employeeID").regex(params.get("employeeID"), "i"));
        }
        if (params.get("category") != null && !params.get("category").isEmpty()) {
        	criteriaList.add(Criteria.where("category").regex(params.get("category"), "i"));
        }
		

//        Sort.Direction direction = sort.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
//        PageRequest pageable = PageRequest.of(page, paging, Sort.by(direction, "id"));
//        Pageable pageable = PageRequest.of(1,5);
//        Query query = new Query();
        Criteria criteria = new Criteria();

        if (!criteriaList.isEmpty()) {
            criteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
        }
//        if(!category.equalsIgnoreCase("")) {
//        	query.addCriteria(Criteria.where("category").is(category));
//        }
        Query query = new Query(criteria);

        query.with(pageable);

        Page<Product> items = mongoUtils.pagingDocuments(query,pageable, Product.class);
//        long count = mongoTemplate.count(query.skip(-1).limit(-1), Item.class);

        return items;
    }
	
}
