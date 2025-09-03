package com.example.buysellProject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // отвечает за прием всех http запросов
public class ProductController {

    @GetMapping("/") // обработка get запросов
    //    "Когда кто-то переходит по корневому URL-адресу
    //    (например, http://localhost:8080/) с помощью GET-запроса
    //    (обычный переход по ссылке в браузере), вызови именно этот метод."

    public String greeting() {
        // Теперь это возвращает не имя шаблона, а строку JSON
        return "Hello, World! This is my API response.";
        //"Не ищи никакой шаблон! Просто возьми то, что вернул метод, и запиши это прямо в тело HTTP-ответа."
    }
}
