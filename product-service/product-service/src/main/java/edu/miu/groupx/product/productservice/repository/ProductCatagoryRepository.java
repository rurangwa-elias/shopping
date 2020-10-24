package edu.miu.groupx.product.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.groupx.product.productservice.models.ProductCatagory;

@Repository
public interface ProductCatagoryRepository extends JpaRepository<ProductCatagory,Long> {
	 
	ProductCatagory save(ProductCatagory category);
	
	ProductCatagory findByName(String name);
	ProductCatagory findById(long id); 
	List<ProductCatagory> findAll();
	
	
	   	   
	void  deleteById(long id);
	void delete(ProductCatagory category);
	    
}
