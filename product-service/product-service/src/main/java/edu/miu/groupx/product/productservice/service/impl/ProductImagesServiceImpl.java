package edu.miu.groupx.product.productservice.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.groupx.product.productservice.models.Product;
import edu.miu.groupx.product.productservice.models.ProductImages;
import edu.miu.groupx.product.productservice.repository.ProductImagesRepository;
import edu.miu.groupx.product.productservice.service.ProductImagesService;
import edu.miu.groupx.product.productservice.service.ProductService;
import edu.miu.groupx.product.productservice.utils.S3Utils;

@Service
@Transactional
public class ProductImagesServiceImpl implements ProductImagesService {
	@Autowired
	ProductImagesRepository productImagesRepository;

	@Autowired
	ProductService productService;

	@Override
	public List<ProductImages> getAllProductImages() {

		return productImagesRepository.findAll();
	}

	@Override
	public ProductImages getProductImagesById(long id) {
		// TODO Auto-generated method stub
		return productImagesRepository.findById(id).get();
	}

	@Override
	public ProductImages saveProductImage(ProductImages image) {
		Long productId= image.getProductId();   
		  String fileName=Paths.get(image.getImagePth()).getFileName().toString();
		  S3Utils.uploadObject(fileName, image.getImagePth());
		 
		 String imageUrl=S3Utils.getBucketName()+fileName;
		 image.setImagePth(imageUrl);
		 Product product= productService.getProductById(productId);
		 List<ProductImages> productImages= product.getPictures();
		 productImages.add(image);
		 productService.save(product);
		 
		
		return productImagesRepository.save(image);
	}

	@Override
	public List<ProductImages> saveProductImages(List<ProductImages> imagesList, Long id) {

		return productImagesRepository.saveAll(imagesList);
	}

}
