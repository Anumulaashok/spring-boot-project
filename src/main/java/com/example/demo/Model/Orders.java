package com.example.demo.Model;

import java.util.Date;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Orders {
	@Id
	private Integer Invoice;
	private Date orderDate;
	private Integer item;
	
	private double totalprice;
	private String url;
	private String address;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Order order;
	
	@JsonIgnore
	public Order getOrder() {
		return order;
		
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
