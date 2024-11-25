package com.example.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/products")
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @GetMapping
    public List<String> getProducts() {
        return Arrays.asList("Product 1", "Product 2", "Product 3");
    }
}
