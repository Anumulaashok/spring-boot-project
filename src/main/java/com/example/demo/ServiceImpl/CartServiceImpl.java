package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.CartDao;
import com.example.demo.Dao.ProductsDao;
import com.example.demo.Exceptions.CartException;
import com.example.demo.Model.Products;
import com.example.demo.Service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDao cartDao;
//	https://github.com/Anumulaashok/spring-boot-project.git
	@Autowired
	private ProductsDao productsDao;

	@Override
	public String addToCart(Products product, Integer UserId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeItemFromCart(Integer id, Integer UserId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String quantity(Integer id, Integer quantity, Integer UserId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
