package com.himalay.product_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.himalay.product_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByCategory(String category);


    List<Product> findByPriceLessThan(Double price);
}