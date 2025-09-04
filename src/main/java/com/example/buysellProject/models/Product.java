package com.example.buysellProject.models;

import lombok.AllArgsConstructor;
import lombok.Data;

//как я понимаю это часть со всеми продуктами
@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String description;
    private int price;
    private String city;
    private String author;
}
