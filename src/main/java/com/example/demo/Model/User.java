package com.example.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String email;
	private String userName;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
	private Cart cart;
//	private Order orders;

	public User(String userName, String password ) {
		super();
		
		this.userName = userName;
		this.password = password;
		
	}
	
	
	
}
