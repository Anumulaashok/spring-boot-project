package com.example.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

	@Id
	@Column(name = "cartitemid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer cartitemId;
	@Column(name = "productid")
	private Integer productId;
	private String productName;
	private String url;
	private double price;
	
	
	private Integer quantity;
	
	@ManyToOne
	@JsonIgnore
	private Cart cart;

	public CartItem(Integer productId, double price, Integer quantity, Cart cart) {
		super();
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.cart = cart;
	}

	@JsonIgnore
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	
	
}
