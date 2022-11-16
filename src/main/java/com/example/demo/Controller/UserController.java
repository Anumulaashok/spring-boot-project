package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.Dao.CategoryDao;
import com.example.demo.Exceptions.ProductException;
import com.example.demo.Model.Products;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ProductService;
import com.example.demo.Service.UserService;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;

@Controller
public class UserController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;


	@GetMapping("get/product/{id}")
	public ResponseEntity<List<Products>> getAllProduct(@PathVariable("id") Integer id) throws ProductException{
		
		
		List<Products> p1= productService.getAllProducts(id);
		
		return new ResponseEntity<List<Products>>(p1,HttpStatus.OK);
		
	}

	@GetMapping("product/{id}")
	public ResponseEntity<Products> getProductById(@PathVariable("id") Integer productId) throws ProductException{
		
		Products p1=productService.getProductByID(productId);
		
		return new ResponseEntity<Products>(p1,HttpStatus.OK);
				
	}
	
	@PutMapping("add/cart/{id}")
	public ResponseEntity<String> addToCart(@PathVariable("id") Integer productId){
		
		return null;
		
	}
	
}
