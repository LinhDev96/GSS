package com.gss.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import entities.Product;


public interface ProductService {
	
	List<Product> getAllProducts() throws Exception;

	void createProducts(Map<String, Object> data) throws Exception;
	
	void deleteProduct(String id) throws Exception;

	Page<Product> getProductByParams(Pageable pageable,Map<String, String> params);
}
