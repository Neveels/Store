package com.example.userservice.controller;

import com.example.userservice.dto.request.ProductRequest;
import com.example.userservice.dto.response.ProductResponse;
import com.example.userservice.dto.response.ProductSearchResponse;
import com.example.userservice.entity.enums.PRODUCT_TYPE;
import com.example.userservice.entity.mongo.Product;
import com.example.userservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity
                .ok()
                .body(productService.addProduct(productRequest));
    }

    @GetMapping("/type/{productTypeEnum}")
    public ResponseEntity<List<Product>> getProductsByType(@PathVariable String productTypeEnum) {
        return ResponseEntity
                .ok()
                .body(productService.getProductByType(PRODUCT_TYPE.valueOf(productTypeEnum)));
    }

    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<ProductSearchResponse>> getProductsViaSearch(@PathVariable String searchString) {
        return ResponseEntity
                .ok()
                .body(productService.getProductsViaSearch(searchString));
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity
                .ok()
                .body(productService.getAllProduct());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProduct(@PathVariable String name) {
        return ResponseEntity
                .ok()
                .body(productService.getProductByName(name));
    }

    @PutMapping()
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity
                .ok()
                .body(productService.updateProduct(productRequest));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
