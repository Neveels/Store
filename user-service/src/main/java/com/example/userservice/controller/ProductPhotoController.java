package com.example.userservice.controller;

import com.example.userservice.dto.request.DeletePhotoRequest;
import com.example.userservice.dto.request.ProductPhotoRequest;
import com.example.userservice.service.ProductPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/19/2023 11:44 AM
 */
@Slf4j
@RestController
@RequestMapping("/products/photo")
public class ProductPhotoController {

    private final ProductPhotoService productPhotoService;

    public ProductPhotoController(ProductPhotoService productPhotoService) {
        this.productPhotoService = productPhotoService;
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestPart("request") ProductPhotoRequest productPhotoRequest) {
        log.debug("Creating new photo from dto: {}", productPhotoRequest);
        return productPhotoService.addProductPhoto(file, productPhotoRequest);
    }


    @GetMapping("/{type}/{name}/{color}/{photo}")
    public ResponseEntity<FileSystemResource> getImage(@PathVariable String type,
                                                @PathVariable String name,
                                                @PathVariable String color,
                                                @PathVariable String photo) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new FileSystemResource(productPhotoService.getProductPhoto(type, name, color, photo)));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping()
    public void deletePhoto(@RequestBody DeletePhotoRequest deletePhotoRequest) {
        productPhotoService.deleteProductPhoto(deletePhotoRequest);
    }

    @GetMapping("/default/{defaultPhotoName}")
    public ResponseEntity<FileSystemResource> getDefaultPhoto(@PathVariable String defaultPhotoName) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new FileSystemResource(productPhotoService.getDefaultProductPhoto(defaultPhotoName)));
    }

}
