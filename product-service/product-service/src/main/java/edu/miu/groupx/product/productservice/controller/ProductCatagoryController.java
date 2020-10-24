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

import edu.miu.groupx.product.productservice.models.ProductCatagory;
import edu.miu.groupx.product.productservice.service.ProductCatagoryService;

@RestController
@RequestMapping("/api")
public class ProductCatagoryController {

	@Autowired
	ProductCatagoryService productCatagoryService;

	@GetMapping("/productCategories")
	public List<ProductCatagory> getCategories() {

		return productCatagoryService.getAll();
	}

	@PostMapping("productCategory/save")
	public ProductCatagory addCategory(@RequestBody ProductCatagory productCatagory) {

		return productCatagoryService.save(productCatagory);
	}

	@GetMapping("productCategories/{id}")
	ProductCatagory getCategory(@PathVariable Long id) {
		return productCatagoryService.getById(id);
	}

	
	  @PutMapping("productCategories/{id}")
	 public  ProductCatagory ProductCatagory(@RequestBody ProductCatagory newCategry, @PathVariable Long id) {
	  
	  return productCatagoryService.productCatagoryUpdate(id, newCategry); }
	 

	@DeleteMapping("productCategories/{id}")
	void deleteCategory(@PathVariable Long id) {
		productCatagoryService.deleteById(id);
		
	}

}
