package com.example.demo.Service;

import com.example.demo.DTO_and_ENUM.CategoryDto;
import com.example.demo.Exceptions.AdminException;
import com.example.demo.Exceptions.CategoryException;
import com.example.demo.Model.Category;

public interface CategoryService {

	
	public Category addCategory(CategoryDto categoryDto,Integer Adminid) throws CategoryException;
	
	
	public Category updateCategory(Category category,Integer AdminId) throws CategoryException;
	
	public String deleteCategory(Integer id,Integer AdminID)throws CategoryException, AdminException;
	
	
}
