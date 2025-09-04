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
    public Map<String, Object> takeAllProductsInfo(@RequestParam(name = "title", required = false) String title) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Добро пожаловать в интернет-магазин.");
        response.put("status", "success");
        response.put("timestamp", LocalDateTime.now());
        response.put("products", productService.findAllProducts(title));
        return response;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Map<String, Object>> takeProductInfo(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Product product = productService.getProductById(id);

        if (product != null) {
            response.put("status", "success");
            response.put("product", product);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Продукт с ID " + id + " не найден");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
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

        try {
            Product product = productService.getProductById(id);

            if (product != null) {
                productService.deleteProduct(id);
                response.put("status", "success");
                response.put("message", "Продукт удален");
                return ResponseEntity.ok(response);
            } else {
                response.put("status", "error");
                response.put("message", "Продукт с ID " + id + " не найден");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Ошибка сервера при удалении: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}