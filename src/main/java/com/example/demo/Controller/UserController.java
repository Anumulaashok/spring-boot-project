package com.example.demo.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.remoting.jaxws.SimpleHttpServerJaxWsServiceExporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO_and_ENUM.AddressDto;
import com.example.demo.DTO_and_ENUM.AdminDto;
import com.example.demo.DTO_and_ENUM.Invoice;
import com.example.demo.DTO_and_ENUM.UserDto;
import com.example.demo.DTO_and_ENUM.UserLoginDto;
import com.example.demo.DTO_and_ENUM.Uuid;
import com.example.demo.Dao.AddressDao;
import com.example.demo.Exceptions.CartException;
import com.example.demo.Exceptions.CategoryException;
import com.example.demo.Exceptions.ProductException;
import com.example.demo.Exceptions.UserException;
import com.example.demo.Model.Address;
import com.example.demo.Model.Cart;
import com.example.demo.Model.Category;
import com.example.demo.Model.Products;
import com.example.demo.Model.User;
import com.example.demo.Service.CartService;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.ProductService;
import com.example.demo.Service.UserLoginService;
import com.example.demo.Service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private OrderService orderService;
	
	
	
	
	@GetMapping("/get/catergories")
	public ResponseEntity<List<Category>> getAllCategories()throws CategoryException{
	
		List<Category> categories= categoryService.getAllCategory();
		
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	
	

	@GetMapping("/get/products/{id}")
	public ResponseEntity<List<Products>> getAllProduct(@PathVariable("id") Integer id) throws ProductException{
		
		
		List<Products> p1= productService.getAllProducts(id);
		
		return new ResponseEntity<List<Products>>(p1,HttpStatus.OK);
		
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Products> getProductById(@PathVariable("id") Integer productId) throws ProductException{
		
		Products p1=productService.getProductByID(productId);
		
		return new ResponseEntity<Products>(p1,HttpStatus.OK);
				
	}
	@PostMapping("/user/add")
	public ResponseEntity<User> addUser(@RequestBody AdminDto userDto) throws UserException{
		userDto.setEmail(userDto.getEmail().toLowerCase());
		userDto.setEmail(userDto.getUserName().toLowerCase());
		User user=	userService.saveUser(userDto);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
	@PutMapping("/user/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UserException{
		User user1= userService.updateUser(user);
		return new ResponseEntity<User>(user1,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/user/delete")
	public ResponseEntity<String> deleteUser(@RequestBody Uuid uuid ) throws UserException
	{
		String str=userService.deleteUser(uuid.getUuid());
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
	
	@PostMapping("/user/login")
	public ResponseEntity<UserLoginDto> userLogin(@RequestBody UserDto userDto) throws UserException{
	
		UserLoginDto ud = userLoginService.userLogin(userDto);
	
		return new ResponseEntity<UserLoginDto>(ud,HttpStatus.OK);		
	}
	
	@DeleteMapping("/user/logout")
	public ResponseEntity<String> userLogout(@RequestBody Uuid uuid) throws UserException{
		
		String ss=	userLoginService.LogOut(uuid.getUuid());
			return new ResponseEntity<String>(ss,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/get/cart/{uuid}")
	public ResponseEntity<Cart> getCart(@PathVariable("uuid") String uuid) throws CartException, UserException{
		
		Cart c=	cartService.getCart(uuid);
		
		return new ResponseEntity<Cart>(c,HttpStatus.OK);
		
	}
	
	@PutMapping("/add/cart/{uuid}")
	public ResponseEntity<User> addToCart(@PathVariable("uuid") String uuid,@RequestParam("productId") Integer id) throws CartException, UserException{
		
		User c=	cartService.addToCart(id, uuid);
		
		return new ResponseEntity<User>(c,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/remove/cart/{uuid}")
	public ResponseEntity<Cart> removeToCart(@PathVariable("uuid") String uuid,@RequestParam("productId") Integer id) throws CartException, UserException{
		
		Cart c=	cartService.removeItemFromCart(id, uuid);
		
		return new ResponseEntity<Cart>(c,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/update/cart/{uuid}")
	public ResponseEntity<Cart> updateCart(@PathVariable("uuid") String uuid,@RequestParam("productId") Integer id,@RequestParam("Quantity") Integer numberofItems) throws CartException, UserException{
		
		Cart c=	cartService.updatequantity(id, numberofItems, uuid);
		
		return new ResponseEntity<Cart>(c,HttpStatus.OK);
		
	}
	
	@PostMapping("/user/add/address")
	public ResponseEntity<Boolean> addAddress(@RequestBody AddressDto address,@RequestHeader String uuid) throws UserException{
		Address address2= new Address(address.getArea(),address.getVillage(),address.getState(),address.getPincode(),address.getMobilenumber());
		boolean b=	orderService.addAddressById(address2, uuid);
		return new ResponseEntity<Boolean>(b,HttpStatus.OK);

	}
	
	@GetMapping("/user/get/address")
	public ResponseEntity<Address> getAddressById(@RequestHeader String uuid) throws UserException{
		
		Address list= orderService.findAddressbyid(uuid);
		return new ResponseEntity<Address>(list,HttpStatus.OK);
	
	}
	
	@DeleteMapping("/user/delete/address")
	public ResponseEntity<Boolean> deleteAddress(@RequestHeader String uuid ,@PathVariable("id") Integer id ) throws UserException
	{
		
		boolean b =	orderService.deleteAddressbyid(uuid, id);
		
		return new ResponseEntity<Boolean>(b ,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getall")
	public ResponseEntity<List<User>> getAllUsers(){
		
		return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/find/address")
	public ResponseEntity<Address> getAllAddress(@RequestHeader String uuid) throws UserException{
		
		Address adds= orderService.findAddressbyid(uuid);
		
		return new ResponseEntity<Address>(adds, HttpStatus.OK);
		
	}
	
	@PostMapping("/order")
	public ResponseEntity<Invoice>  orderByuuid(@RequestHeader String uuid) throws UserException{
		
		
		Invoice invoice= orderService.orderTheItems(uuid);
		return new ResponseEntity<Invoice>(invoice,HttpStatus.OK);
		
	}

}
