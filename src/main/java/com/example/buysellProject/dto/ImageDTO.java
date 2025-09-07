package com.example.buysellProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    //private Long id;
    //private String name;
    //private String originalFileName;
    private Long size;
    private String contentType;
    private byte[] bytes;
}