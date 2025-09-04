package com.example.buysellProject.controllers;

import com.example.buysellProject.models.Product;
import com.example.buysellProject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/api")
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

    // Добавьте метод для удаления
    @DeleteMapping("/product/delete/{id}")
    public Map<String, Object> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Продукт удален");
        return response;
    }
}
