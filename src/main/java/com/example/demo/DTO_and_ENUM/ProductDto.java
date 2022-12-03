package com.example.demo.DTO_and_ENUM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

	private String name;
	private String url;
	private String url2;
	private String desciption;
	private double price;
	private double price2;
	private boolean isAvail;
}
