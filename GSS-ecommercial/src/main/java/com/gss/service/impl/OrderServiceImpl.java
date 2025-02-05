package com.gss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gss.service.OrderService;

import entities.Customer;
import entities.Order;
import utils.MongoUtils;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private MongoUtils mongoUtils;
	
	public void getOrderBy() {
		
	}
	
	@Override
	public List<Order> getAllOrders() throws Exception {
		// TODO Auto-generated method stub
		List<Order> result = mongoUtils.findAll(Order.class);
		return result;
	}
	
	@Override
	public void saveDocument(Order order) {
		mongoUtils.saveDocument(order);
	}

}
