package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.CartDao;
import com.example.demo.Dao.CartItemDao;
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
	
	@Autowired
	private CartItemDao cartItemDao;

	@Override
	public User addToCart(Integer id, String Uuid) throws CartException, UserException {
		
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
		System.out.println("Hello");
		
		CartItem item= new CartItem(products.getId(), products.getPrice(), 1,cart);
		if(cart.getCartItem().size()<=0) {
			cart.setSize(1);
			cart.setTotal(products.getPrice());
			cart.getCartItem().add(item);	
		}
		else {
		List<CartItem>	items=cart.getCartItem();
		boolean b=false;
		double ctotal=0;
		int cquantity=0;
		for(CartItem i:items) {
			if(i.getProductId()==products.getId()) {
				
				i.setPrice(i.getPrice()+products.getPrice());
				i.setQuantity(i.getQuantity()+1);				
				b= true;
			}
			ctotal+=i.getPrice();
			cquantity+=i.getQuantity();
		}
		if(b==false) {
			
			items.add(item);
			cart.setSize(cart.getCartItem().size());
			cart.setTotal(cart.getTotal()+item.getPrice());
			ctotal+=item.getPrice();
			cquantity+=1;
		}
		cart.setSize(cquantity);
		cart.setTotal(ctotal);
		}
		cartDao.save(cart);
		return user;
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
			double ctotal=0;
			int cquantity=0;
			CartItem ii= new CartItem();
			for(CartItem i:items) {
				if(i.getProductId()==products.getId()) {
					ii=i;
				}
				else {
					ctotal+=i.getPrice();
					cquantity+=i.getQuantity();
				}
			}
			if(ii!=null)
			cart.getCartItem().remove(ii);
			cart.setSize(cquantity);
			cart.setTotal(ctotal);
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
			if(i.getProductId()==products.getId()&&i.getQuantity()==1&&quantity==0) {
				removeItemFromCart(id, UuId);
				break;
			}
			if(i.getProductId()==products.getId()&&quantity>0) {
				
				i.setPrice(i.getPrice()*quantity);
				i.setQuantity(quantity);
				cart.setSize(cart.getSize()-i.getQuantity()+quantity);
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
