package com.example.buysellProject.controllers;

import com.example.buysellProject.models.Product;
import com.example.buysellProject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public Map<String, Object> greeting() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Добро пожаловать в интернет-магазин.");
        response.put("status", "success");
        response.put("timestamp", LocalDateTime.now());
        response.put("products", productService.takeProductList());
        return response;
    }

    @PostMapping("/product/create")
    public Map<String, Object> createProduct(@RequestBody Product product) {
        productService.saveProduct(product);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Продукт успешно создан");
        response.put("product", product);
        return response;
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        boolean wasDeleted = productService.deleteProduct(id);

        if (wasDeleted) {
            response.put("status", "success");
            response.put("message", "Продукт удален");
            return ResponseEntity.ok(response); // 200 OK
        } else {
            response.put("status", "error");
            response.put("message", "Продукт с ID " + id + " не найден");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // 404 Not Found
        }
    }
}
