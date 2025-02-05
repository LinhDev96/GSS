package com.gss.service;

import java.util.List;

import entities.Order;

public interface OrderService {
	
	List<Order> getAllOrders() throws Exception;

	void saveDocument(Order order);
}
