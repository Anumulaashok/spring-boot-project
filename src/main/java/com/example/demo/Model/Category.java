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
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String description;
	private String image1;
	private String image2;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Admin admin;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
	@JsonIgnore
	private List<Products> product=new ArrayList<Products>();

	public Category(String name, String description, String image1,String image2,Admin admin) {
		super();
		this.name = name;
		this.description = description;
		this.image1 = image1;
		this.image2 = image2;
		this.admin=admin;
	}


	
	
	
}
