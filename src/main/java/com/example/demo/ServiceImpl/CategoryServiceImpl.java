package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO_and_ENUM.CategoryDto;
import com.example.demo.DTO_and_ENUM.ProductDto;
import com.example.demo.Dao.AdminDao;
import com.example.demo.Dao.CategoryDao;
import com.example.demo.Dao.LogInDao;
import com.example.demo.Dao.ProductsDao;
import com.example.demo.Exceptions.AdminException;
import com.example.demo.Exceptions.CategoryException;
import com.example.demo.Model.Admin;
import com.example.demo.Model.Category;
import com.example.demo.Model.LogIn;
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
	
	@Autowired
	private LogInDao logInDao;
	
	
	@Override
	public Category addCategory(CategoryDto categoryDto,String uuid) throws CategoryException {
	
		Category c1= categoryDao.findByName(categoryDto.getName());

		if(c1!=null) {
			throw new CategoryException("Category Already Exist with the name "+categoryDto.getName());
		}
		
		LogIn login = logInDao.findByUuid(uuid).orElseThrow(()-> new CategoryException("your not a valid admin"));
		
		Admin A1= admindao.findByUserName(login.getUsername());
		
		if(A1==null)
				new CategoryException("Admin Not Avail With the id -> "+A1.getUserName());
		
		Category category= new Category(categoryDto.getName(),categoryDto.getDescription(), categoryDto.getImage1(),categoryDto.getImage2(),A1);
		
		return categoryDao.save(category);
		
	}


	@Override
	public List<Category> getAllCategory() throws CategoryException {
		List<Category> categories=	categoryDao.findAll();
		if(categories.size()==0) {
			throw new CategoryException("No Category found");
		}
		return categories;
	}

	@Override
	public Category updateCategory(Category category,String uuid) throws CategoryException {
		
		LogIn login = logInDao.findByUuid(uuid).orElseThrow(()-> new CategoryException("your not a valid admin"));
		
		Admin A1= admindao.findByUserName(login.getUsername());
		
		if(A1==null)
			throw	new CategoryException("Admin Not Avail With the id -> "+A1.getUserName());
		
		
		Category c1= categoryDao.findById(category.getId()).orElseThrow(()-> new CategoryException("Category Not Foound with the id"));
		if(A1.getCategory().equals(c1))
			throw new CategoryException("your not a valid admin");
		c1.setName(category.getName());
		c1.setDescription(category.getDescription());
		c1.setImage1(category.getImage1());
		
		return categoryDao.save(c1);
		
	}

	@Override
	public String deleteCategory(Integer id,String uuid) throws CategoryException, AdminException {
		
		LogIn login = logInDao.findByUuid(uuid).orElseThrow(()-> new CategoryException("your not a valid admin"));
		
		Admin A1= admindao.findByUserName(login.getUsername());
		
		if(A1==null)
			throw	new CategoryException("Admin Not Avail");
		
		Category c1= categoryDao.findById(id).orElseThrow(()-> new CategoryException("Category Not Foound with the id"));
		
		if(A1.getCategory().equals(c1))
			throw new CategoryException("your not a valid admin");
		
		
		categoryDao.delete(c1);
		
		return "Category Deleted";
	}



	
	
}
