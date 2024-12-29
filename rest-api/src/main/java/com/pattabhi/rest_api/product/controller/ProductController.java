package com.pattabhi.rest_api.product.controller;

import com.pattabhi.rest_api.product.entity.Product;
import com.pattabhi.rest_api.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    @Cacheable("products")
    public Product getProductById(@PathVariable int id) {
        return productRepository.findById(id).get();
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("/product/{id}")
    @CacheEvict(value = "products")
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
