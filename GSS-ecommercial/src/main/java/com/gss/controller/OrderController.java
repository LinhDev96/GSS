package com.gss.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gss.service.OrderService;

import entities.Order;

@RestController
@CrossOrigin
@RequestMapping(value = "/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllOrders()
			throws Exception {
		List<Order> orders = new ArrayList<>();
		orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}
}
