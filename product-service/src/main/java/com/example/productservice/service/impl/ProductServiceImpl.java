package com.example.productservice.service.impl;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 4:27 PM
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductByType(Product.Type productTypeEnum) {
        return productRepository
                .findAll()
                .stream()
                .filter(product -> product.getType().equals(productTypeEnum))
                .toList();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
