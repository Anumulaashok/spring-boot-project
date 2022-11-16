package com.example.demo.Service;

import com.example.demo.Exceptions.CartException;
import com.example.demo.Model.Products;

public interface CartService {
	
	public String addToCart(Products product,Integer UserId)throws  CartException;
	
	public String removeItemFromCart(Integer id,Integer UserId)throws CartException;

	public String quantity(Integer id,Integer quantity,Integer UserId)throws CartException;
	
}
