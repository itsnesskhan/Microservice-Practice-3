package com.userservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

	private String id;

	private String status;

	private Integer userId;

	private Integer productId;
	
	private ProductDTO product;

	private LocalDateTime created_at;
}