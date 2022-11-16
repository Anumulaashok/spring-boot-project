package com.example.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

	@Id	
	private Integer productId;
	
	private double price;
	
	private Integer quantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
	
	
	
}
