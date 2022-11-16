package com.example.demo.DTO_and_ENUM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private String name;
	private String description;
	private String image1;
}
