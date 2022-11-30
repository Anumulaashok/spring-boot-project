package com.example.demo.DTO_and_ENUM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

	private String UserName;
	private String Password;
	private String email;
	private String name;
	
}
