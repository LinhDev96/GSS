package com.gss.service;

import java.util.Map;

import entities.Customer;

public interface CustomerService {
	
	void saveCustomer(Customer customer);

//	void createProducts(Map<String, Object> data) throws Exception;
//	
//	void deleteProduct(String id) throws Exception;
//
//	Page<Product> getProductByParams(Pageable pageable,Map<String, String> params);
	
	Customer findByEmail(String email);

	void updateCustomer(String documentId, Map<String, ? extends Object> dataToSave);
}
