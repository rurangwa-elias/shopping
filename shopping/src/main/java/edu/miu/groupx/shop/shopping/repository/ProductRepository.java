package edu.miu.groupx.shop.shopping.repository;

import edu.miu.groupx.shop.shopping.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
