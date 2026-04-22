package com.mavericks.controller;

import com.mavericks.model.Product;
import com.mavericks.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Seed products on first run (same as Node.js version)
    @Bean
    public CommandLineRunner seedProducts() {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.saveAll(List.of(
                    Product.builder().id("p1").name("Double Zipper Hoodie").category("Hoodies").price(89).stock(50).colors("Onyx,Crimson,Emerald").sizes("XS,S,M,L,XL").build(),
                    Product.builder().id("p2").name("Mavericks Crop Top").category("Crop Tops").price(45).stock(80).colors("Black,White,Grey").sizes("XS,S,M,L").build(),
                    Product.builder().id("p3").name("Baggy Street Pants").category("Pants").price(75).stock(60).colors("Black,Khaki,Navy").sizes("S,M,L,XL").build(),
                    Product.builder().id("p4").name("Statement Tee").category("Shirts").price(39).stock(120).colors("Black,White").sizes("XS,S,M,L,XL").build()
                ));
                System.out.println("✓ Products seeded");
            }
        };
    }

    // GET /api/products
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ok(Map.of("success", true, "data", products));
    }

    // GET /api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable String id) {
        return productRepository.findById(id)
            .map(p -> ok(Map.of("success", true, "data", p)))
            .orElse(notFound("Product not found"));
    }

    private ResponseEntity<Map<String, Object>> ok(Map<String, Object> body) {
        return ResponseEntity.ok(body);
    }

    private ResponseEntity<Map<String, Object>> notFound(String msg) {
        return ResponseEntity.status(404).body(Map.of("success", false, "message", msg));
    }
}
