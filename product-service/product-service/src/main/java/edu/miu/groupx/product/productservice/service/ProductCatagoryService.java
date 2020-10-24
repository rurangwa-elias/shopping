package edu.miu.groupx.product.productservice.service;

import java.util.List;

import edu.miu.groupx.product.productservice.models.ProductCatagory;

public interface ProductCatagoryService {
	
	ProductCatagory save(ProductCatagory category);
	
	ProductCatagory getByName(String name);
	ProductCatagory getById(long id); 
	List<ProductCatagory> getAll();
	
	ProductCatagory productCatagoryUpdate(long id, ProductCatagory productCatagory);
	   	   
	void  deleteById(long id);
	void delete(ProductCatagory category);

}
