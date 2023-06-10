package com.example.userservice.service;

import com.example.userservice.dto.request.ProductRequest;
import com.example.userservice.dto.response.ProductResponse;
import com.example.userservice.dto.response.ProductSearchResponse;
import com.example.userservice.entity.enums.PRODUCT_TYPE;
import com.example.userservice.entity.mongo.Product;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest product);


    void deleteProduct(String id);

    List<Product> getProductByType(PRODUCT_TYPE productTypeEnum);

    List<Product> getAllProduct();

    Product getProductByName(String name);

    ProductResponse updateProduct(ProductRequest productRequest);

    List<ProductSearchResponse> getProductsViaSearch(String searchString);
}
