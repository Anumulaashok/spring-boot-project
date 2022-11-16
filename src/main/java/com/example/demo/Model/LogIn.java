package com.example.demo.Model;

import java.time.LocalDate;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogIn {
	@Id
	private String username;
	private String password;
	private String uuid;
	private LocalDate localDate=LocalDate.now();

}
