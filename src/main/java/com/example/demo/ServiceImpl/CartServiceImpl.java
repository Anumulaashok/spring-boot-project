package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.CartDao;
import com.example.demo.Dao.LogInDao;
import com.example.demo.Dao.ProductsDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.Exceptions.CartException;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.Cart;
import com.example.demo.Model.CartItem;
import com.example.demo.Model.LogIn;
import com.example.demo.Model.Products;
import com.example.demo.Model.User;
import com.example.demo.Service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDao cartDao;
//	https://github.com/Anumulaashok/spring-boot-project.git
	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LogInDao logInDao;
	
	

	@Override
	public String addToCart(Integer id, String Uuid) throws CartException, UserException {
		
		LogIn userLogeedIn= logInDao.findByUuid(Uuid).orElseThrow(()-> new UserException("User Not found"));
		
		User user= userDao.findByUserName(userLogeedIn.getUsername());
		Products products= productsDao.findById(id).orElseThrow(()-> new CartException("Item not found"));
		
		if(!products.isAvail()) {
			throw new CartException("Item is not avilable");
		}
		
		if(user==null) {
			throw new CartException("Internal error try log out and log in");
		}
		
		Cart cart= user.getCart();
		CartItem item= new CartItem(products.getId(), products.getPrice(), 1, cart);
		if(cart.getSize()<=0) {
			cart.setId(user.getId());
			cart.setSize(1);
			cart.setTotal(products.getPrice());
			cart.getCartItem().add(item);
			cart.setUser(user);
			
		}
		else {
		List<CartItem>	items=cart.getCartItem();
		for(CartItem i:items) {
			if(i.getProductId()==products.getId()) {
				i.setPrice(i.getPrice()+products.getPrice());
				i.setQuantity(i.getQuantity()+1);
				cart.setSize(cart.getSize()+1);
				cart.setTotal(cart.getTotal()+i.getPrice());
				break;
			}
		}
		
		}
		cartDao.save(cart);
		
		return cart.toString();
	}

	@Override
	public Cart removeItemFromCart(Integer id, String Uuid) throws CartException, UserException {
		
		LogIn userLogeedIn= logInDao.findByUuid(Uuid).orElseThrow(()-> new UserException("User Not found"));
		
		User user= userDao.findByUserName(userLogeedIn.getUsername());
		Products products= productsDao.findById(id).orElseThrow(()-> new CartException("Item not found"));
		
		if(!products.isAvail()) {
			throw new CartException("Item is not avilable");
		}
		
		if(user==null) {
			throw new CartException("Internal error try log out and log in");
		}
		
		Cart cart= user.getCart();
		
		CartItem item= new  CartItem();
		if(cart.getSize()<=0) {
			throw new UserException("Cart is Empty");
			
		}
		else {
			List<CartItem>	items=cart.getCartItem();
		for(CartItem i:items) {
			if(i.getProductId()==products.getId()) {
				
				cart.setSize(cart.getSize()-1);
				cart.setTotal(cart.getTotal()-i.getPrice());
				item=i;
				break;
			}
		}
		if(item==null) {
			throw new CartException("Item not found");
		}
		items.remove(item);
	
		
		}
		cartDao.save(cart);
		
		return cart;
	}

	@Override
	public Cart updatequantity(Integer id, Integer quantity, String UuId) throws CartException, UserException {
	
		LogIn userLogeedIn= logInDao.findByUuid(UuId).orElseThrow(()-> new UserException("User Not found"));
		
		User user= userDao.findByUserName(userLogeedIn.getUsername());
		Products products= productsDao.findById(id).orElseThrow(()-> new CartException("Item not found"));
		
		if(!products.isAvail()) {
			throw new CartException("Item is not avilable");
		}
		
		if(user==null) {
			throw new CartException("Internal error try log out and log in");
		}
		
		Cart cart= user.getCart();
		CartItem item= new CartItem(products.getId(), products.getPrice(), 1, cart);
		if(cart.getSize()<=0) {
			
			throw new UserException("Cart is Empty");
			
		}
		else {
		List<CartItem>	items=cart.getCartItem();
		boolean b=false;
		for(CartItem i:items) {
			if(i.getProductId()==products.getId()&&i.getQuantity()==1) {
				removeItemFromCart(id, UuId);
				break;
			}
			if(i.getProductId()==products.getId()&&i.getQuantity()>1) {
				i.setPrice(i.getPrice()-products.getPrice());
				i.setQuantity(i.getQuantity()-1);
				cart.setSize(cart.getSize()-1);
				cart.setTotal(cart.getTotal()-i.getPrice());
				b=true;
				break;
			}
		}
		
		
		}
		cartDao.save(cart);
		
		return cart;
		
	}

	@Override
	public Cart getCart(String uuid) throws CartException, UserException {

		LogIn userLogeedIn= logInDao.findByUuid(uuid).orElseThrow(()-> new UserException("User Not found"));
		
		User user= userDao.findByUserName(userLogeedIn.getUsername());
		
		if(user==null) {
			throw new CartException("Internal error try log out and log in");
		}
		
		Cart cart= user.getCart();
		if(cart.getSize()<=0) {
			throw new CartException("Cart is Empty");
		}
		
		return cart;
	}


	
	
	
}
