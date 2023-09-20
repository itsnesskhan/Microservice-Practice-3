package com.orderservice.controller;

import com.orderservice.dto.OrderDTO;
import com.orderservice.payloads.ApiResponse;
import com.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService OrderService;
	
	@Value("${server.port}")
	private String servicePort;
	
	@PostMapping
	public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderDTO OrderDTO){
		ApiResponse Order = OrderService.createOrder(OrderDTO);
		return ResponseEntity.ok(Order);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> getAllOrder(@RequestBody OrderDTO OrderDTO){
		ApiResponse Orders = OrderService.getAllOrder();
		return ResponseEntity.ok(Orders);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getAllOrder(@PathVariable String id){
		ApiResponse Orders = OrderService.getOrderById(id);
		return ResponseEntity.ok(Orders);
	}
	
	@GetMapping("/user/{id}/feign")
	public ResponseEntity<ApiResponse> getAllOrderOfUserFeign(@PathVariable Integer id){
		System.out.println("order-service with port "+servicePort+" got called");
		ApiResponse Orders = OrderService.getAllOrderOfUserUsingFeignClient(id);
		return ResponseEntity.ok(Orders);
	}

	@GetMapping("/user/{id}/rest")
	public ResponseEntity<ApiResponse> getAllOrderOfUserRestTemplate(@PathVariable Integer id){
		System.out.println("order-service with port "+servicePort+" got called");
		ApiResponse Orders = OrderService.getAllOrderOfUserUsingRestTemplate(id);
		return ResponseEntity.ok(Orders);
	}
	
	@GetMapping("/user/{id}/rest/load-balanced")
	public ResponseEntity<ApiResponse> getAllOrderOfUserRestTemplateLoadBalanced(@PathVariable Integer id){
		System.out.println("order-service with port "+servicePort+" got called");
		ApiResponse Orders = OrderService.getAllOrderOfUserUsingRestTemplateLoadBalanced(id);
		return ResponseEntity.ok(Orders);
	}
	
	
	
}
