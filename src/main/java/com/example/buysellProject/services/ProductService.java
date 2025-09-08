package com.example.buysellProject.services;

import com.example.buysellProject.dto.ProductDTO;
import com.example.buysellProject.mappers.ProductMapper;
import com.example.buysellProject.models.Image;
import com.example.buysellProject.models.Product;
import com.example.buysellProject.repositories.ImageRepository;
import com.example.buysellProject.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ImageRepository imageRepository;

    public List<ProductDTO> findAllProducts(String title) {

        List<Product> productList = (title != null ? productRepository.findByTitleContainingIgnoreCase(title) : productRepository.findAll());
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product i:productList)
        {
            productDTOList.add(productMapper.toDTO(i));
        }
        return productDTOList;
    }

    public Product saveProduct(ProductDTO product, MultipartFile file) throws IOException {

        Product savedProduct = productRepository.save(productMapper.fromDTO(product));

        if (file != null && file.getSize() != 0) {
            Image image = toImageEntity(file);
            image.setProduct(savedProduct);

            Image savedImage = imageRepository.save(image);

            savedProduct.setImage(savedImage);
            savedProduct.setDateOfCreated(LocalDateTime.now());
            productRepository.save(savedProduct);
        }
        return savedProduct;
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO getProductById(Long id) {
        Product product =  productRepository.findById(id).orElse(null);
        if (product!=null) return productMapper.toDTO(product);
        return null;
    }
}
