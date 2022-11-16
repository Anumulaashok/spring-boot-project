package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Products;

@Repository
public interface ProductsDao extends JpaRepository<Products, Integer>{

	@Query("select p from Products p where p.category=:id order by p.price asc")
	public List<Products> getProductsInASC(@Param("id") Integer id);

	@Query("select p from Products p where p.category=:id order by p.price desc")
	public List<Products> getProductsInDESC(@Param("id") Integer id);
}
