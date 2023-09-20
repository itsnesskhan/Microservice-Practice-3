package com.orderservice.service;

import com.orderservice.dto.OrderDTO;
import com.orderservice.payloads.ApiResponse;

public interface OrderService {

	ApiResponse createOrder(OrderDTO OrderDTO);

	ApiResponse getOrderById(String id);

	ApiResponse getAllOrder();
	
	ApiResponse getAllOrderOfUserUsingRestTemplate(Integer id);
	
	ApiResponse getAllOrderOfUserUsingRestTemplateLoadBalanced(Integer id);
	
	ApiResponse getAllOrderOfUserUsingFeignClient(Integer id);
}
