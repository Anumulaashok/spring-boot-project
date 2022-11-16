package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

	
	public Category findByName(String name);
}
