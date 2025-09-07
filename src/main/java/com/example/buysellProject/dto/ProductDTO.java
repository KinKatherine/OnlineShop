package com.example.buysellProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private int price;
    private String city;
    private String author;
    private ImageDTO image;
    private LocalDateTime dateOfCreated;
}