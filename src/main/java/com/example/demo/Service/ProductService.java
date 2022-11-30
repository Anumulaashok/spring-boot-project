package com.example.demo.Service;

import java.util.List;

import com.example.demo.DTO_and_ENUM.ProductDto;
import com.example.demo.Exceptions.AdminException;
import com.example.demo.Exceptions.CategoryException;
import com.example.demo.Exceptions.ProductException;
import com.example.demo.Model.Products;

public interface ProductService {

	public boolean AddProduct(Integer categoryId,String uuid, ProductDto product)throws CategoryException, AdminException;
	
	public Products updateProduct(Integer ProductId,ProductDto product,String uuid)throws ProductException;
	
	public String deleteProduct(Integer ProductId,String uuid)throws ProductException;
	
	public Products ChangeStatus(Integer id,boolean bool,String uuid)throws ProductException;
	
	public List<Products> getAllProducts(Integer id)throws ProductException;
	
	public Products getProductByID(Integer id) throws ProductException;
	
	public List<Products> getProdutsInSortedASE(Integer id) throws ProductException;
	
	public List<Products> getProdutsInSortedDESC(Integer id) throws ProductException;
}
