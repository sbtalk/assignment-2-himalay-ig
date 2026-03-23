package com.himalay.product_service.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.himalay.product_service.entity.Product;
import com.himalay.product_service.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    // Constructor Injection (simple + clean)
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET /products  -> Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET /products/{id} -> Get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productRepository.findByCategory(category);
    }


    @GetMapping("/cheaper-than/{price}")
    public List<Product> getCheaperThan(@PathVariable Double price) {
        return productRepository.findByPriceLessThan(price);
    }


    // POST /products -> Add new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        // Since you removed @GeneratedValue, client must send ID in JSON
        return productRepository.save(product);
    }
}