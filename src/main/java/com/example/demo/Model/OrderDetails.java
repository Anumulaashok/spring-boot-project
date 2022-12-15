package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "orderDetails")
	private List<Orders> orders= new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "order")
	private User user;
	
	
	@JsonIgnore
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public OrderDetails(List<Orders> orders, User user) {
		super();
		this.orders = orders;
		this.user = user;
	}
	
	
	
	
}
