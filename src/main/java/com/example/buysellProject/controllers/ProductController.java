package com.example.buysellProject.controllers;

import com.example.buysellProject.dto.ProductDTO;
import com.example.buysellProject.models.Product;
import com.example.buysellProject.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        response.put("status", "success");
        response.put("products", productService.findAllProducts(title));
        return response;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Map<String, Object>> takeProductInfo(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        ProductDTO productDTO = productService.getProductById(id);

        if (productDTO != null) {
            response.put("status", "success");
            response.put("product", productDTO);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Продукт с ID " + id + " не найден");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/product/create")
    public Map<String, Object> createProduct(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") int price,
            @RequestParam("city") String city,
            @RequestParam("author") String author) throws IOException {

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCity(city);
        product.setAuthor(author);

        productService.saveProduct(product, file);

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
            ProductDTO productDTO = productService.getProductById(id);

            if (productDTO != null) {
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