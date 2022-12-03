package com.example.demo.Controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO_and_ENUM.AdminDto;
import com.example.demo.DTO_and_ENUM.CategoryDto;
import com.example.demo.DTO_and_ENUM.ProductDto;
import com.example.demo.DTO_and_ENUM.UserDto;
import com.example.demo.DTO_and_ENUM.UserLoginDto;
import com.example.demo.DTO_and_ENUM.Uuid;
import com.example.demo.Exceptions.AdminException;
import com.example.demo.Exceptions.CategoryException;
import com.example.demo.Exceptions.ProductException;
import com.example.demo.Model.Admin;
import com.example.demo.Model.Category;
import com.example.demo.Model.LogIn;
import com.example.demo.Model.Products;
import com.example.demo.Service.AdminService;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
public class AdminController {

	private static final Products Pro = null;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/admin/add")
	public ResponseEntity<Admin> addAdmin(@RequestBody AdminDto admin2) throws AdminException
	{
		
		Admin admin1=adminService.saveAdmin(admin2);
		
		return new ResponseEntity<Admin>(admin1,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/admin/update")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin ) throws AdminException
	{
		
		Admin admin1= adminService.updateAdminUserName(admin);
		
		return new ResponseEntity<Admin>(admin1 , HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<String> deleteAdmin(@PathVariable Integer id) throws AdminException{
		
		String str= adminService.deleteAdmin(id);
		
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	
	
	@PostMapping("/category/add/{uuid}")
	public ResponseEntity<Category> addCategory(@RequestBody CategoryDto categoryDto,@PathVariable("uuid") String id) throws CategoryException{
		
		
		Category c1= categoryService.addCategory(categoryDto, id);
		
		return new ResponseEntity<Category>(c1,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/category/update/{id}")
	public ResponseEntity<Category> updateTheCategory(@RequestBody Category category,@PathVariable("uuid") String id) throws CategoryException{
		
		
		Category c1= categoryService.updateCategory(category, id);
		
		return new ResponseEntity<Category>(c1,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/category/delete")
	public ResponseEntity<String> deleteTheCategory(@RequestParam("categoryId") Integer category,@PathVariable("uuid") String id) throws CategoryException, AdminException{
		
		
		String c1= categoryService.deleteCategory(category, id);
		
		return new ResponseEntity<String>(c1,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/product/add") 
	public ResponseEntity<String> addProduct(@RequestBody ProductDto product,@RequestHeader("uuid") String uuid,@RequestParam("CategoryId") Integer categoryId ) throws CategoryException, AdminException{
		System.out.println(uuid);
		boolean b1= productService.AddProduct(categoryId,uuid, product);
		
		return new ResponseEntity<String>("Product added succesfully ",HttpStatus.OK);
	}
	
	@PutMapping("/product/update")
	public ResponseEntity<Products> updateProduct(@RequestBody ProductDto Product ,@PathVariable("productid") Integer productId,@RequestParam("uuid") String uuid) throws ProductException 
	{
		
		Products prosctsss= productService.updateProduct(productId,Product, uuid);
		
		return new ResponseEntity<Products>(prosctsss , HttpStatus.OK);
	}
	
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id,@PathVariable("uuid") String uuid) throws AdminException, ProductException{
		
		String str= productService.deleteProduct(id,uuid);
		
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}
	
	@PostMapping("admin/login")
	public ResponseEntity<UserLoginDto> adminLogIn(@RequestBody UserDto adminDto) throws AdminException{ 
		UserLoginDto login =adminService.adminLogIn(adminDto);
		return new ResponseEntity<UserLoginDto>(login,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("admin/logout")
	public ResponseEntity<String> adminLogOut(@RequestBody Uuid uu) throws AdminException{
		String str= adminService.adminLogOut(uu.getUuid());
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
}
