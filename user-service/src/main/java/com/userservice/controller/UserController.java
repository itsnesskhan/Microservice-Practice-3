package com.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.dto.UserDTO;
import com.userservice.payloads.ApiResponse;
import com.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> createUser(@RequestBody UserDTO userDTO){
		ApiResponse user = userService.createUser(userDTO);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> getAllUser(@RequestBody UserDTO userDTO){
		ApiResponse users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getAllUser(@PathVariable Integer id){
		ApiResponse users = userService.getUserById(id);
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}/orders/rest")
	public ResponseEntity<ApiResponse> getAllUserOrdersRest(@PathVariable Integer id){
		ApiResponse users = userService.getAllUserOrderRest(id);
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}/orders/rest/load-balanced")
	public ResponseEntity<ApiResponse> getAllUserOrdersRestLoadBalanced(@PathVariable Integer id){
		ApiResponse users = userService.getAllUserOrderRestLoadBalance(id);
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}/orders/feign")
	public ResponseEntity<ApiResponse> getAllUserOrdersFeign(@PathVariable Integer id){
		ApiResponse users = userService.getAllUserOrderFeign(id);
		return ResponseEntity.ok(users);
	}
	
}
