package com.prodcutservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

	private Integer pid;
	
	private String name;
	
	private String price;
	
	private String quantity;
}
