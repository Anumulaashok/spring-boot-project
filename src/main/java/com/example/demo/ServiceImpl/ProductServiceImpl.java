package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO_and_ENUM.ProductDto;
import com.example.demo.Dao.AdminDao;
import com.example.demo.Dao.CategoryDao;
import com.example.demo.Dao.ProductsDao;
import com.example.demo.Exceptions.CategoryException;
import com.example.demo.Exceptions.ProductException;
import com.example.demo.Model.Admin;
import com.example.demo.Model.Category;
import com.example.demo.Model.Products;
import com.example.demo.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private AdminDao admindao;

	@Override
	public boolean AddProduct(Integer id, Integer AdminId, ProductDto product) throws CategoryException {

		//
		Admin A1 = admindao.findById(AdminId)
				.orElseThrow(() -> new CategoryException("Admin Not Avail With the id -> " + AdminId));

		Category c1 = categoryDao.findById(id).orElseThrow(() -> new CategoryException("Category Not Found"));

		System.out.println(c1.toString());

		Products p1 = new Products(product.getName(), product.getUrl(), product.getDesciption(), product.getPrice(),
				product.isAvail());

		p1.setCategory(c1);

		Products pro = productsDao.save(p1);
		return true;

	}

	@Override
	public Products updateProduct(Integer ProductId, ProductDto product) throws ProductException {

		Products p1 = productsDao.findById(ProductId)
				.orElseThrow(() -> new ProductException("NO Product found with the Id ->" + ProductId));

		p1.setName(product.getName());
		p1.setDesciption(product.getDesciption());
		p1.setPrice(product.getPrice());
		p1.setUrl(product.getUrl());
		return productsDao.save(p1);

	}

	@Override
	public String deleteProduct(Integer ProductId) throws ProductException {

		Products p1 = productsDao.findById(ProductId)
				.orElseThrow(() -> new ProductException("NO Product found with the Id ->" + ProductId));

		productsDao.delete(p1);
		return "Product Deleted Succesfully";
	}

	@Override
	public Products ChangeStatus(Integer ProductId, boolean bool) throws ProductException {

		Products p1 = productsDao.findById(ProductId)
				.orElseThrow(() -> new ProductException("NO Product found with the Id ->" + ProductId));

		p1.setAvail(bool);

		return productsDao.save(p1);

	}

	@Override
	public List<Products> getAllProducts(Integer id) throws ProductException {

		Category c1 = categoryDao.findById(id).orElseThrow(() -> new ProductException("No Category"));

		// System.out.println(c1.toString());

		return c1.getProduct();
	}

	public Products getProductByID(Integer id) throws ProductException {

		Products p1 = productsDao.findById(id).orElseThrow(() -> new ProductException("Product not found"));

		return p1;
	}

	public List<Products> getProdutsInSortedASE(Integer id) throws ProductException {
		
		return productsDao.getProductsInASC(id);

	}
	

	public List<Products> getProdutsInSortedDESC(Integer id) throws ProductException {
		
		return productsDao.getProductsInDESC(id);

	}

}
