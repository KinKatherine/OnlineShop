package com.example.buysellProject.mappers;

import com.example.buysellProject.dto.ImageDTO;
import com.example.buysellProject.dto.ProductDTO;
import com.example.buysellProject.models.Image;
import com.example.buysellProject.models.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCity(product.getCity());
        dto.setAuthor(product.getAuthor());
        //dto.setDateOfCreated(product.getDateOfCreated());

        if (product.getImage() != null) {
            dto.setImage(toImageDTO(product.getImage()));
        }

        return dto;
    }

    public Product fromDTO(ProductDTO productDTO){
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCity(productDTO.getCity());
        product.setAuthor(productDTO.getAuthor());
        //product.setDateOfCreated(productDTO.getDateOfCreated());
        return product;

    }

    private ImageDTO toImageDTO(Image image) {
        ImageDTO dto = new ImageDTO();
        //dto.setId(image.getId());
        //dto.setName(image.getName());
        //dto.setOriginalFileName(image.getOriginalFileName());
        dto.setSize(image.getSize());
        dto.setContentType(image.getContentType());
        dto.setBytes(image.getBytes());
        return dto;
    }
}
