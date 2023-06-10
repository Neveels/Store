package com.example.productservice.service;

import com.example.productservice.entity.Product;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 4:26 PM
 */
public interface ProductService {

    Product addProduct(Product product);

    Product getProduct(Integer id);

    void deleteProduct(Integer id);

    List<Product> getProductByType(Product.Type productTypeEnum);

    List<Product> getAllProduct();
}
