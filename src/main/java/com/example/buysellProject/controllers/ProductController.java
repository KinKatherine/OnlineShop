package com.example.buysellProject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;          // ← Добавьте этот импорт
import java.util.HashMap;

@RestController  // отвечает за прием всех http запросов
public class ProductController {

    @GetMapping("/") // обработка get запросов
    //    "Когда кто-то переходит по корневому URL-адресу
    //    (например, http://localhost:8080/) с помощью GET-запроса
    //    (обычный переход по ссылке в браузере), вызови именно этот метод."

    public Map<String, Object> greeting() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Всем привет. Рада видеть вас на ночных посиделках :)");
        response.put("status", "success");
        response.put("timestamp", LocalDateTime.now());
        return response;
    }
}
