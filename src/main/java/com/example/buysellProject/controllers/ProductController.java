package com.example.buysellProject.controllers;

import com.example.buysellProject.dto.ProductDTO;
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

    @GetMapping("/v1/products")
    public Map<String, Object> takeAllProductsInfo(@RequestParam(name = "title", required = false) String title)
    {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("products", productService.findAllProducts(title));
        return response;
    }

    @GetMapping("/v1/products/{id}")
    public ResponseEntity<Map<String, Object>> takeProductInfo(@PathVariable Long id)
    {
        Map<String, Object> response = new HashMap<>();
        ProductDTO productDTO = productService.getProductById(id);

        if (productDTO != null)
        {
            response.put("status", "success");
            response.put("product", productDTO);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Продукт с ID " + id + " не найден");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/v1/products/create")
    public Map<String, Object> createProduct(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") int price,
            @RequestParam("city") String city,
            @RequestParam("author") String author) throws IOException
    {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle(title);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setCity(city);
        productDTO.setAuthor(author);

        productDTO.setId(productService.saveProduct(productDTO, file).getId());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Продукт c id " +productDTO.getId()+ " успешно создан");
        return response;
    }

    @DeleteMapping("/v1/products/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable Long id)
    {
        Map<String, Object> response = new HashMap<>();

        try {
            ProductDTO productDTO = productService.getProductById(id);

            if (productDTO != null)
            {
                productService.deleteProduct(id);
                response.put("status", "success");
                response.put("message", "Продукт удален");
                return ResponseEntity.ok(response);
            } else
            {
                response.put("status", "error");
                response.put("message", "Продукт с ID " + id + " не найден");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

        } catch (Exception e)
        {
            response.put("status", "error");
            response.put("message", "Ошибка сервера при удалении: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}