package com.example.buysellProject.controllers;

import com.example.buysellProject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;          // ← Добавьте этот импорт
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public Map<String, Object> greeting() {
        Map<String, Object> response = new HashMap<>();
        response.put("message","Добро пожаловать в интернет-магазин.");
        response.put("status", "success");
        response.put("timestamp", LocalDateTime.now());
        response.put("products", productService.takeProductList()); // Добавляем продукты в JSON
        return response;
    }
}
