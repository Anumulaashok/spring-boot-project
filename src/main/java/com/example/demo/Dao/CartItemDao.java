package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Model.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Integer>{

	@Query(value = "delete from CartItem where cartitemId=:id")
	public String removeCartitemId(@Param("id") int cartItemid);
	
	@Query(value = "update CartItem set quantity=?1 where cartitemId=?2")
	public String changebyquantity(int quantity,int cartitemid);
}

