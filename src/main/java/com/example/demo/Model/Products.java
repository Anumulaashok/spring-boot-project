package com.example.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Data
@NoArgsConstructor
@Entity
public class Products {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String name;
	private String url;
	private String desciption;
	private double price;
	private boolean isAvail;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Category category;
	

	public Products(String name, String url, String desciption, double price,boolean isAvail) {
		super();
		this.name = name;
		this.url = url;
		this.desciption = desciption;
		this.price = price;
		this.isAvail=isAvail;
	}

	@JsonIgnore
	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvail() {
		return isAvail;
	}

	public void setAvail(boolean isAvail) {
		this.isAvail = isAvail;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	
	
	
	
}
