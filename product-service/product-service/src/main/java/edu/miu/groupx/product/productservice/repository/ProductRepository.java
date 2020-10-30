package edu.miu.groupx.product.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.Category;
import edu.miu.groupx.product.productservice.models.ProductStatus;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	

	Product findByName(String productName);

	/*
	 * @Query(value = "select p from Product p  where p.status = :status ")
	 * List<Product> getNew(@Param(value = "status") ProductStatus productStatus);
	 * 
	 * @Query(value = "select p from Product p  where p.status = :status ")
	 * List<Product> getApproved(@Param(value = "status") ProductStatus
	 * productStatus);
	 * 
	 * @Query(value = "select p from Product p  where p.status = :status ")
	 * List<Product> getRejected(@Param(value = "status") ProductStatus
	 * productStatus);
	 */

	@Query(value = "select p from Product p  where p.category = :category ")
	List<Product> getByCategory(@Param(value = "category") Category category);

	/*
	 * @Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.addedOn, ' ', p.status, ' ', p.price) LIKE %?1%"
	 * )
	 * 
	 * List<Product> searchProducts(@Param("keyword")String keyword);
	 */

	@Query("SELECT p from  Product p where p.name like '%'||:keyword||'%' or p.Price like cast(:keyword as double) or addedOn like '%'||:keyword||'%'")
	List<Product> searchProducts(@Param(value = "keyword") String keyword);

}
