package com.example.demoapi.services;

import com.example.demoapi.models.Product;
import com.example.demoapi.repositories.ProductRepository;
import jakarta.persistence.PrePersist;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }
}
