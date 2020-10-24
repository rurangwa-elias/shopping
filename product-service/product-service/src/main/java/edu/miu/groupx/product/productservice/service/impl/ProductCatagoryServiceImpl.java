package edu.miu.groupx.product.productservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.product.productservice.models.ProductCatagory;
import edu.miu.groupx.product.productservice.repository.ProductCatagoryRepository;
import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.service.ProductCatagoryService;

@Service
@Transactional
public class ProductCatagoryServiceImpl implements ProductCatagoryService {
	@Autowired
	ProductCatagoryRepository productCatagoryRepository;

	@Override
	public ProductCatagory save(ProductCatagory category) {
		
		return productCatagoryRepository.save(category);
	}

	@Override
	public ProductCatagory getByName(String name) {
		
		return productCatagoryRepository.findByName(name);
	}

	@Override
	public ProductCatagory getById(long id) {
		
		return productCatagoryRepository.findById(id);
	}

	@Override
	public List<ProductCatagory> getAll() {
		
		return productCatagoryRepository.findAll();
	}

	
	  @Override 
	  public ProductCatagory productCatagoryUpdate(long id, ProductCatagory newProductCatagory) 
	  { 
		  ProductCatagory ProductCatagory= getById(id);
		  ProductCatagory.setName(newProductCatagory.getName());
		  ProductCatagory.setQuantity(newProductCatagory.getQuantity()); 
		  return productCatagoryRepository.save(ProductCatagory);
	  
	  }
	 

	@Override
	public void deleteById(long id) {
		productCatagoryRepository.deleteById(id);
		
	}

	@Override
	public void delete(ProductCatagory category) {
		productCatagoryRepository.delete(category);
		
	}

}
