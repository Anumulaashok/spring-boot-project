package com.example.demo.DTO_and_ENUM;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyError {

	private String msg;
	private String desciption;
	private LocalDate date;
}
