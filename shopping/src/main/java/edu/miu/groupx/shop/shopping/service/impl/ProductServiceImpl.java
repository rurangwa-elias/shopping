package edu.miu.groupx.shop.shopping.service.impl;

import edu.miu.groupx.shop.shopping.exception.ShoppingException;
import edu.miu.groupx.shop.shopping.models.Product;
import edu.miu.groupx.shop.shopping.repository.ProductRepository;
import edu.miu.groupx.shop.shopping.service.ProductService;;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ShoppingException("Object not found"));
    }
}
