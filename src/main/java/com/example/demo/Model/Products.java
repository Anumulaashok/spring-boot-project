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



@NoArgsConstructor
@Entity
public class Products {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String name;
	private String url;
	private String url2;
	private String description;
	private double price;
	private double price2;
	private boolean isAvail;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Category category;
	

	public Products(String name, String url, String desciption, double price,boolean isAvail) {
		super();
		this.name = name;
		this.url = url;
		this.description = desciption;
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
		return description;
	}

	public void setDesciption(String desciption) {
		this.description = desciption;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public double getPrice2() {
		return price2;
	}

	public void setPrice2(double price2) {
		this.price2 = price2;
	}
	
	
	
	
}
