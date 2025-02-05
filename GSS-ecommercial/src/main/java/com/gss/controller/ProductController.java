package com.gss.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gss.service.ProductService;
//import com.gss.service.impl.HashMap;

import entities.Product;
//import entities.products;
import lombok.extern.slf4j.Slf4j;
import utils.CommonUtil;

@RestController
@CrossOrigin
@RequestMapping(value = "/product")
//@Slf4j
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	public ResponseEntity<Object> getAllProduct()
			throws Exception {
		List<Product> products = new ArrayList<>();
		products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Object> searchProduct()
			throws Exception {
		List<Product> products = new ArrayList<>();
		products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/getBy")
	public Page<Product> getProductByParams(            
			@RequestParam(required = false) String category,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) int pageSize,
            @RequestParam(required = false) int page,
            @RequestParam(required = false) Map<String,String> requires)
			throws Exception {
//		List<Product> products = new ArrayList<>();
//		products = productService.getAllProducts();
		Pageable pageable = CommonUtil.buildPageable(requires);
	
		return productService.getProductByParams(pageable,requires);
//		return ResponseEntity.ok(products);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> createProduct(@RequestBody Map<String, Object> data)
			throws Exception {
//		Product products = new Product();
		productService.createProducts(data);
		return ResponseEntity.ok("ok1");
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable  String id)
			throws Exception {
//		Product products = new Product();
		productService.deleteProduct(id);
		return ResponseEntity.ok("ok2");
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteMultipleProduct(@RequestParam  String ids)
			throws Exception {
		Map<String, Object> response = new HashMap<>();
		String[] idList = ids.split(",");
		for(String id : idList) {
			productService.deleteProduct(id);
		}
		
//		Map<String, String> response = new HashMap<>();
//	    if (result.getDeletedCount() > 0) {
	        response.put("message", "Delete successful");
	        return ResponseEntity.ok(response);
//	    } else {
//	        response.put("message", "Product not found");
//	        return ResponseEntity.status(404).body(response);
//	    }
//		return ResponseEntity.ok("");
	}
}
