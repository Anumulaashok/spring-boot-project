package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO_and_ENUM.CategoryDto;
import com.example.demo.Exceptions.AdminException;
import com.example.demo.Exceptions.CategoryException;
import com.example.demo.Model.Category;

public interface CategoryService {

	
	public Category addCategory(CategoryDto categoryDto,Integer Adminid) throws CategoryException;
	
	public List<Category> getAllCategory()throws CategoryException;
	
	public Category updateCategory(Category category,Integer AdminId) throws CategoryException;
	
	public String deleteCategory(Integer id,Integer AdminID)throws CategoryException, AdminException;
	
	
}
