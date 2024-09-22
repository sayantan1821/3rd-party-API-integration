package com.sayantan.productservice.contollers;

import com.sayantan.productservice.commons.Auth;
import com.sayantan.productservice.dtos.ErrorResponseDTO;
import com.sayantan.productservice.dtos.UserDTO;
import com.sayantan.productservice.exceptions.InvalidTokenException;
import com.sayantan.productservice.models.Product;
import com.sayantan.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
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
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) throws Exception {
        try {
//            UserDTO user = auth.validateToken(token);
            Product product = productService.getProductById(id);
            if(product == null) {
                return new ResponseEntity<>("Product not found with id " + id, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
//        } catch (InvalidTokenException err) {
//            ErrorResponseDTO errorResponse = new ErrorResponseDTO(err.getMessage(), "");
//            return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
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

    @PostMapping()
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
