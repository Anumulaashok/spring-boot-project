package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Products;

@Repository
public interface ProductsDao extends JpaRepository<Products, Integer>{

}
