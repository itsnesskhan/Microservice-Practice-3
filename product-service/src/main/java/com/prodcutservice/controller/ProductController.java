package com.prodcutservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodcutservice.dto.ProductDTO;
import com.prodcutservice.payloads.ApiResponse;
import com.prodcutservice.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService ProductService;

	@Value("${server.port}")
	private String servicePort;
	
	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO ProductDTO){
		ApiResponse Product = ProductService.createProduct(ProductDTO);
		return ResponseEntity.ok(Product);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> getAllProduct(@RequestBody ProductDTO ProductDTO){
		ApiResponse Products = ProductService.getAllProduct();
		return ResponseEntity.ok(Products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable Integer id){
		System.out.println("product-service with port "+servicePort+" got called");
		ApiResponse Products = ProductService.getProductById(id);
		return ResponseEntity.ok(Products);
	}
	
}
