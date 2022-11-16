package com.example.demo.DTO_and_ENUM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

	private String email;
	private String userName;
	private String password;
}
