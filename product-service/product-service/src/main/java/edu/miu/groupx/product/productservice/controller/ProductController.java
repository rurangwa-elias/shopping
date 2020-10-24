package edu.miu.groupx.product.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	

	 @Autowired
	  private  ProductService productService;
	 
	 
	 @PostMapping("/product/add")
	    public Product addProduct(@RequestBody Product product){
	        
	    return productService.save(product);
	 }
	 
	 @GetMapping("/products")
	    public  List<Product> getProductss() {
	        return productService.getAllProducts();
	    }
	 
	 @GetMapping("/products/{id}")
	    Product getProduct(@PathVariable Long id) {
	        return productService.getProductById(id);
	    }
	 
	/*
	 * @GetMapping("/search/products/{id}") List<Product>
	 * searchProducts(@PathVariable String keyword) { return
	 * productService.search(keyword); }
	 */
	 
	
	  @PutMapping("/products/{id}") 
	  public Product ProductUpdate (@RequestBody Product newProduct, @PathVariable Long id) {
	  
	  return productService.updateProduct(id, newProduct); 
	  }
	 
	 @DeleteMapping("products/{id}")
	    void deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	    }
	}

