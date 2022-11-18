package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO_and_ENUM.ProductDto;
import com.example.demo.Exceptions.CategoryException;
import com.example.demo.Exceptions.ProductException;
import com.example.demo.Model.Products;

public interface ProductService {

	public boolean AddProduct(Integer categoryId,Integer AdminId, ProductDto product)throws CategoryException;
	
	public Products updateProduct(Integer ProductId,ProductDto product)throws ProductException;
	
	public String deleteProduct(Integer ProductId)throws ProductException;
	
	public Products ChangeStatus(Integer id,boolean bool)throws ProductException;
	
	public List<Products> getAllProducts(Integer id)throws ProductException;
	
	public Products getProductByID(Integer id) throws ProductException;
	
	public List<Products> getProdutsInSortedASE(Integer id) throws ProductException;
	
	public List<Products> getProdutsInSortedDESC(Integer id) throws ProductException;
}
