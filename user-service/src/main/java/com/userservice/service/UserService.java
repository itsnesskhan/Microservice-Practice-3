package com.userservice.service;

import com.userservice.dto.UserDTO;
import com.userservice.payloads.ApiResponse;

public interface UserService {

	ApiResponse createUser(UserDTO userDTO);
	
	ApiResponse getUserById(Integer id);
	
	ApiResponse getAllUser();
	
	ApiResponse getAllUserOrderRest(Integer id);
	
	ApiResponse getAllUserOrderRestLoadBalance(Integer id);
	
	ApiResponse getAllUserOrderFeign(Integer id);

}
