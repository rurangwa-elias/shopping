package edu.miu.groupx.product.productservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductCatagory;
import edu.miu.groupx.product.productservice.models.ProductStatus;
import edu.miu.groupx.product.productservice.repository.ProductCatagoryRepository;
import edu.miu.groupx.product.productservice.repository.ProductRepository;
import edu.miu.groupx.product.productservice.service.ProductService;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	 @Autowired
	    ProductRepository productRepository;
	 @Autowired
	 ProductCatagoryRepository productCatagoryRepository;

	@Override
	public Product save(Product product) {
		Product actualProduct = null;
		Long productCategoryId=product.getProductCatagoryId();
			if(productCategoryId!=null) {
			ProductCatagory productCatagory=productCatagoryRepository.findById(productCategoryId).get();
			//how to set the quantity
			product.setProductCatagory(productCatagory);
			System.out.println(product.getProductCatagory().getId());
			actualProduct=productRepository.save(product);
			productCatagory.setQuantity(productCatagory.getQuantity()+1);
		}
		
		return actualProduct;
		
	}

	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(long id) {
		
		return productRepository.findById(id);
                
	}

	@Override
	public Product getProductByName(String name) {
		
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getProductByCategory(ProductCatagory productCatagory) {
		
		return productRepository.getByCategory(productCatagory);
	}

	@Override
	public List<Product> getNewProducts(ProductStatus productStatus) {
		
		return productRepository.getNew(productStatus);
	}

	@Override
	public List<Product> getApprovedProducts(ProductStatus productStatus) {
		
		return productRepository.getApproved(productStatus);
	}

	@Override
	public List<Product> getRejectedProducts(ProductStatus productStatus) {
		
		return productRepository.getRejected(productStatus);
	}

	
	  @Override public Product updateProduct(long id, Product newProduct) {
		  
		  Product ActualProduct=getProductById(id);
		  ActualProduct.setName(newProduct.getName());
		  ActualProduct.setPrice(newProduct.getPrice());
		  ActualProduct.setProductCatagory(newProduct.getProductCatagory());
		  ActualProduct.setAddedOn(newProduct.getAddedOn()); 
		  ActualProduct.setStatus(newProduct.getStatus());
		  ActualProduct.setDescription(newProduct.getDescription());
		  ActualProduct.setImageUrl(newProduct.getImageUrl()); 
		   
		  return save(ActualProduct); 
		  
	  }
	 

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
		
	}

	
	  @Override public List<Product> search(String keyword) {
	  
	  return productRepository.searchProducts(keyword); }
	 
}
