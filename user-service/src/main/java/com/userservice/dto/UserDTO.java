package com.userservice.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	@JsonProperty("user_id")
	private Integer id;
	
	private String name;
	
	private String email;
	
	private List<OrderDTO> orders;
}
