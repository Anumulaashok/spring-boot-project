package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO_and_ENUM.AdminDto;
import com.example.demo.DTO_and_ENUM.CategoryDto;
import com.example.demo.DTO_and_ENUM.ProductDto;
import com.example.demo.Exceptions.AdminException;
import com.example.demo.Exceptions.CategoryException;
import com.example.demo.Model.Admin;
import com.example.demo.Model.Category;
import com.example.demo.Model.Products;
import com.example.demo.Service.AdminService;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/admin/add")
	public ResponseEntity<Admin> addAdmin(@RequestBody AdminDto admin) throws AdminException
	{
		
		Admin admin1=adminService.saveAdmin(admin);
		
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
	
	
	@PostMapping("category/add/{id}")
	public ResponseEntity<Category> addCategory(@RequestBody CategoryDto categoryDto,@PathVariable("id") Integer id) throws CategoryException{
		
		
		Category c1= categoryService.addCategory(categoryDto, id);
		
		return new ResponseEntity<Category>(c1,HttpStatus.CREATED);
		
	}
	
	@PostMapping("product/add")
	public ResponseEntity<String> addProduct(@RequestBody ProductDto product,@RequestParam("AdminId") Integer adminid,@RequestParam("CategoryId") Integer categoryId ) throws CategoryException{
		
		boolean b1= productService.AddProduct(categoryId, adminid, product);
		
		return new ResponseEntity<String>("Product added succesfully ",HttpStatus.OK);
	}
	
}