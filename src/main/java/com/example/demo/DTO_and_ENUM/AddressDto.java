package com.example.demo.DTO_and_ENUM;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
	
//	private String email;
	private String area;
	private String village;
	private String state;
	private String pincode;
	private String mobilenumber;
}
