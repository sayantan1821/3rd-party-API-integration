package com.sayantan.productservice.services;

import com.sayantan.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws Exception;
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product);
    Product createProduct(Product product);

}
