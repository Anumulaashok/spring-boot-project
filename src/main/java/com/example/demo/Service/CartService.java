package com.example.demo.Service;

import com.example.demo.Exceptions.CartException;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.Cart;
import com.example.demo.Model.User;

public interface CartService {
	
	public User addToCart(Integer  id,String Uuid)throws  CartException, UserException;
	
	public Cart removeItemFromCart(Integer id,String Uuid)throws CartException, UserException;

	public Cart updatequantity(Integer id,Integer quantity,String UuId)throws CartException, UserException;
	
	public Cart getCart(String uuid)throws CartException,UserException;
}
