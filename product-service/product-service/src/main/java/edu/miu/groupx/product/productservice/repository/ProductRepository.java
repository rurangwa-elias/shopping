package edu.miu.groupx.product.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductCatagory;
import edu.miu.groupx.product.productservice.models.ProductStatus;
@Repository
public interface ProductRepository  extends JpaRepository<Product, Long>{
		
	Product save( Product product);
		List<Product> findAll();
		Product findByName(String productName);
		Product findById(long id);
		
		@Query(value = "select p from Product p  where p.status = :status ")
	    List<Product> getNew(@Param(value = "status") ProductStatus productStatus);
	  
	  	@Query(value = "select p from Product p  where p.status = :status ")
	    List<Product> getApproved(@Param(value = "status") ProductStatus productStatus);
	  
	  	@Query(value = "select p from Product p  where p.status = :status ")
	    List<Product> getRejected(@Param(value = "status") ProductStatus productStatus);
	  
	  	@Query(value = "select p from Product p  where p.productCatagory = :catagory ")
	    List<Product> getByCategory(@Param(value = "catagory") ProductCatagory productCatagory);
	  	
	/*
	 * @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.addedOn, ' ', p.status, ' ', p.price, ' ', p.price) LIKE %?1%"
	 * )
	 * 
	 * List<Product> searchProducts(@Param("keyword")String keyword);
	 */
		
	  	
	  
	  
	  
	  

}
