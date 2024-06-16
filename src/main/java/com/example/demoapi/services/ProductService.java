package com.example.demoapi.services;

import com.example.demoapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product);

}
