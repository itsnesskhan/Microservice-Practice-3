package com.userservice.service.impl;

import java.util.Arrays;
import java.util.List;

import com.userservice.feignclient.OrderClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.dto.OrderDTO;
import com.userservice.dto.UserDTO;
import com.userservice.entity.User;
import com.userservice.payloads.ApiResponse;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private OrderClient orderClient;
	
	@Autowired
	private DiscoveryClient discoveryClient; // will fetch only first instance from discovery service,
											//so no load balancing
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
						//pick random instance when ever fetch instance with it from discovery service

	@Override
	public ApiResponse createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user = userRepository.save(user);
		return ApiResponse.builder().data(modelMapper.map(user, UserDTO.class)).message("User Created Successfully!")
				.build();
	}

	@Override
	public ApiResponse getUserById(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("user does not exist with id : " + id));
		return ApiResponse.builder().data(modelMapper.map(user, UserDTO.class)).message("User with id " + id).build();
	}

	@Override
	public ApiResponse getAllUser() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDtos = Arrays.asList(modelMapper.map(users, UserDTO[].class));
		return ApiResponse.builder().data(userDtos).message("All users").build();
	}

// using rest template
// no load balancing
	@Override
	public ApiResponse getAllUserOrderRest(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id : " + id));
		ApiResponse apiResponse = restTemplate.getForObject("http://localhost:8082/order-app/api/order/user/{id}/rest",
				ApiResponse.class, id);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		OrderDTO[] orderDTOs = objectMapper.convertValue(apiResponse.getData(), OrderDTO[].class);
		userDTO.setOrders(Arrays.asList(orderDTOs));
		return ApiResponse.builder().data(userDTO).message("All User orders! with id : "+id).build();
	}
	
//load balancing in rest template	
	@Override
	public ApiResponse getAllUserOrderRestLoadBalance(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id : " + id));
		ApiResponse apiResponse = restTemplate.getForObject("http://ORDER-SERVICE/order-app/api/order/user/{id}/rest",
				ApiResponse.class, id);
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		OrderDTO[] orderDTOs = objectMapper.convertValue(apiResponse.getData(), OrderDTO[].class);
		userDTO.setOrders(Arrays.asList(orderDTOs));
		return ApiResponse.builder().data(userDTO).message("All User orders! with id : "+id).build();
	}
	
	
// using rest template
// no load balancing
//load balancing if room hard code path in feign client
	@Override
	public ApiResponse getAllUserOrderFeign(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id : " + id));
		ApiResponse apiResponse = orderClient.getAllOrderOfUserFeign(id).getBody();
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		OrderDTO[] orderDTOs = objectMapper.convertValue(apiResponse.getData(), OrderDTO[].class);
		userDTO.setOrders(Arrays.asList(orderDTOs));
		return ApiResponse.builder().data(userDTO).message("All User orders! with id : "+id).build();
	}

}
