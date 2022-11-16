package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Dao.CategoryDao;
import com.example.demo.Exceptions.CartException;
import com.example.demo.Exceptions.ProductException;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.Cart;
import com.example.demo.Model.Products;
import com.example.demo.Service.CartService;
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

	@Autowired
	private CartService cartService;

	@GetMapping("get/products/{id}")
	public ResponseEntity<List<Products>> getAllProduct(@PathVariable("id") Integer id) throws ProductException{
		
		
		List<Products> p1= productService.getAllProducts(id);
		
		return new ResponseEntity<List<Products>>(p1,HttpStatus.OK);
		
	}

	@GetMapping("product/{id}")
	public ResponseEntity<Products> getProductById(@PathVariable("id") Integer productId) throws ProductException{
		
		Products p1=productService.getProductByID(productId);
		
		return new ResponseEntity<Products>(p1,HttpStatus.OK);
				
	}
	
	@GetMapping("get/cart/{uuid}")
	public ResponseEntity<Cart> getCart(@PathVariable("uuid") String uuid) throws CartException, UserException{
		
		Cart c=	cartService.getCart(uuid);
		
		return new ResponseEntity<Cart>(c,HttpStatus.OK);
		
	}
	
	@PutMapping("add/cart/{uuid}")
	public ResponseEntity<String> addToCart(@PathVariable("uuid") String uuid,@RequestParam("productId") Integer id) throws CartException, UserException{
		
		String c=	cartService.addToCart(id, uuid);
		
		return new ResponseEntity<String>(c,HttpStatus.OK);
		
	}
	
	@DeleteMapping("remove/cart/{uuid}")
	public ResponseEntity<Cart> removeToCart(@PathVariable("uuid") String uuid,@RequestParam("productId") Integer id) throws CartException, UserException{
		
		Cart c=	cartService.removeItemFromCart(id, uuid);
		
		return new ResponseEntity<Cart>(c,HttpStatus.OK);
		
	}
	
	
	@PutMapping("update/cart/{uuid}")
	public ResponseEntity<Cart> updateCart(@PathVariable("uuid") String uuid,@RequestParam("productId") Integer id,@RequestParam("Quantity") Integer numberofItems) throws CartException, UserException{
		
		Cart c=	cartService.updatequantity(id, numberofItems, uuid);
		
		return new ResponseEntity<Cart>(c,HttpStatus.OK);
		
	}
	
	
}
