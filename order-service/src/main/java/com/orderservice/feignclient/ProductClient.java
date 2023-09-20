package com.orderservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.orderservice.dto.ProductDTO;
import com.orderservice.payloads.ApiResponse;

//@FeignClient(name = "Product-Client", url = "http://localhost:8081", path = "product-app/api") no load balancing
@FeignClient(name = "product-service", path = "product-app/api") //with load balancing
public interface ProductClient {

	@PostMapping("/product")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDTO ProductDTO);

	@GetMapping("/product")
	public ResponseEntity<ApiResponse> getAllProduct(@RequestBody ProductDTO ProductDTO);

	@GetMapping("/product/{id}")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable Integer id);
	
}
