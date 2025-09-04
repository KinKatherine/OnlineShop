package com.example.buysellProject.services;

import com.example.buysellProject.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private long ID = 0;


    {
        products.add(new Product(++ID,"PlayStation","Simple description",67000,"Minsk","Tomas"));
        products.add(new Product(++ID,"Iphone 8","Simple description",24000,"Moscow","Nikita"));
    }

    public List<Product> takeProductList(){
        return products;
    }

    public void saveProduct(Product product){
        product.setId(++ID);
        products.add(product);
    }

    public void deleteProduct(Long id){
        products.removeIf(product -> product.getId().equals(id));
    }
}
