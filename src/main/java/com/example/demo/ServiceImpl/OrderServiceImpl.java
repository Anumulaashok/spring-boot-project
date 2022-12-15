package com.example.demo.ServiceImpl;

import java.awt.font.NumericShaper.Range;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO_and_ENUM.AddressType;
import com.example.demo.DTO_and_ENUM.Invoice;
import com.example.demo.DTO_and_ENUM.Order_Status;
import com.example.demo.DTO_and_ENUM.UserLoginDto;
import com.example.demo.Dao.AddressDao;
import com.example.demo.Dao.CartDao;
import com.example.demo.Dao.LogInDao;
import com.example.demo.Dao.OrderDao;
import com.example.demo.Dao.OrderDeatilsDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.Address;
import com.example.demo.Model.Cart;
import com.example.demo.Model.CartItem;
import com.example.demo.Model.LogIn;
import com.example.demo.Model.OrderDetails;
import com.example.demo.Model.Orders;
import com.example.demo.Model.User;
import com.example.demo.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LogInDao logInDao;
	
	@Autowired
	private OrderDeatilsDao orderDao;
	
	@Autowired
	private CartDao  cartDao;

	@Override
	public Address findAddressbyid(String uuid) throws UserException {
		
		LogIn userLogin= logInDao.findByUuid(uuid).orElseThrow(()-> new UserException("Not a valid User"));
		User user= userDao.findByUserName(userLogin.getUsername());
		return user.getAddress();
		
		
	}

	@Override
	public boolean addAddressById(Address address, String uuid) throws UserException {
		LogIn userLogin= logInDao.findByUuid(uuid).orElseThrow(()-> new UserException("Not a valid User"));
		User user= userDao.findByUserName(userLogin.getUsername());
		Address a=user.getAddress();
		if(a!=null) {
			addressDao.delete(a);
		}
		user.setAddress(address);
		userDao.save(user);
		return true;
	}
 
	@Override
	public boolean deleteAddressbyid(String uuid, Integer id) throws UserException {
//		LogIn userLogin= logInDao.findByUuid(uuid).orElseThrow(()-> new UserException("Not a valid User"));
//		User user= userDao.findByUserName(userLogin.getUsername());
//		
		return true;
	}


	@Override
	public Invoice orderTheItems(String uuid) throws UserException {
	LogIn ll=	logInDao.findByUuid(uuid).orElseThrow(()-> new UserException("user Not found"));
	User user1=	userDao.findByUserName(ll.getUsername());
		if(user1.getCart().getSize()<=0) {
			throw new UserException("Nothing in cart please Add something");
		}
		
		Invoice invoice = new Invoice();
		invoice.setTotal(user1.getCart().getTotal());
		Address address2 = user1.getAddress();
		invoice.setShipping_address(user1.getAddress().toString());
		
		OrderDetails details= user1.getOrder();
		Cart items=user1.getCart();
		List<CartItem> li= new ArrayList<>();
		for(CartItem o: items.getCartItem()) {
			invoice.getProducts_Details().put(o.getProductName(), o.getPrice());
			Orders orders= new Orders(o.getCartitemId(), LocalDate.now(),o.getProductId(),Order_Status.Order_Placed, o.getPrice(), o.getUrl(), user1.getAddress().toString());
			orders.setOrderDetails(details);
			details.getOrders().add(orders);
			li.add(o);
			
		}
		items.getCartItem().removeAll(li);
		items.setSize(0);
		items.setTotal(0.0);
		cartDao.save(items);
		orderDao.save(details);
		invoice.setInvoiceID((int)Math.random());
		return invoice;
	}
	
}
