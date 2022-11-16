package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO_and_ENUM.CategoryDto;
import com.example.demo.DTO_and_ENUM.ProductDto;
import com.example.demo.Dao.AdminDao;
import com.example.demo.Dao.CategoryDao;
import com.example.demo.Dao.ProductsDao;
import com.example.demo.Exceptions.AdminException;
import com.example.demo.Exceptions.CategoryException;
import com.example.demo.Model.Admin;
import com.example.demo.Model.Category;
import com.example.demo.Model.Products;
import com.example.demo.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private AdminDao admindao;

	@Autowired
	private ProductsDao productsDao;
	@Override
	public Category addCategory(CategoryDto categoryDto,Integer Id) throws CategoryException {
	
		Category c1= categoryDao.findByName(categoryDto.getName());
		
		Admin A1= admindao.findById(Id).orElseThrow(()-> new CategoryException("Admin Not Avail With the id -> "+Id));
		
		if(c1!=null) {
			throw new CategoryException("Category Already Exist with the name "+categoryDto.getName());
		}
		
		Category category= new Category(categoryDto.getName(),categoryDto.getDescription(), categoryDto.getImage1(),A1);
		
		return categoryDao.save(category);
		
	}

	

	@Override
	public Category updateCategory(Category category,Integer AdminId) throws CategoryException {
		
		Category c1= categoryDao.findById(category.getId()).orElseThrow(()-> new CategoryException("Category Not Foound with the id"));
		
		c1.setName(category.getName());
		c1.setDescription(category.getDescription());
		c1.setImage1(category.getImage1());
		
		return categoryDao.save(c1);
		
	}

	@Override
	public String deleteCategory(Integer id,Integer AdminID) throws CategoryException, AdminException {
		
		Category c1= categoryDao.findById(id).orElseThrow(()-> new CategoryException("Category Not Foound with the id"));
		
		Admin admin1=	admindao.findById(AdminID).orElseThrow(()-> new AdminException("You Dont have a Permission"));
		
		categoryDao.delete(c1);
		
		return "Category Deleted";
	}
	
	
}
