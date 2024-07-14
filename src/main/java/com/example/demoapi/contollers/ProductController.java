package com.example.demoapi.contollers;

import com.example.demoapi.commons.Auth;
import com.example.demoapi.dtos.ErrorResponseDTO;
import com.example.demoapi.dtos.UserDTO;
import com.example.demoapi.exceptions.InvalidTokenException;
import com.example.demoapi.models.Product;
import com.example.demoapi.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final Auth auth;

    public ProductController(@Qualifier("selfProductService") ProductService productService, Auth auth) {
        this.productService = productService;
        this.auth = auth;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id, @RequestHeader("authToken") String token) throws Exception {
        try {
            UserDTO user = auth.validateToken(token);
            Product product = productService.getProductById(id);
            if(product == null) {
                return new ResponseEntity<>("Product not found with id " + id, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (InvalidTokenException err) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(err.getMessage(), "");
            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
        } catch (RuntimeException err) {
            System.out.println(err.getMessage());
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(err.getMessage(), getStackTraceAsString(err));
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product productBody) {
        return productService.replaceProduct(id, productBody);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    private String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}
