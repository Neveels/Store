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

    @PostMapping("/preview")
    public String uploadPreviewFile(@RequestParam("file") MultipartFile file,
                                    @RequestPart("request") ProductPhotoRequest productPhotoRequest) {
        log.debug("Creating new photo from dto: {}", productPhotoRequest);
        return productPhotoService.addPreviewPhoto(file, productPhotoRequest);
    }


    @GetMapping("/{productId}/{photoName}")
    public ResponseEntity<FileSystemResource> getImage(@PathVariable String productId,
                                                       @PathVariable String photoName) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new FileSystemResource(productPhotoService.getProductPhoto(productId, photoName)));
    }

    @GetMapping("/preview/{productId}/{photoName}")
    public ResponseEntity<FileSystemResource> getPreviewImage(@PathVariable String productId,
                                                       @PathVariable String photoName) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new FileSystemResource(productPhotoService.getPreviewProductPhoto(productId, photoName)));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping()
    public void deletePhoto(@RequestBody DeletePhotoRequest deletePhotoRequest) {
        productPhotoService.deleteProductPhoto(deletePhotoRequest);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/preview")
    public void uploadPreviewFile(@RequestBody DeletePhotoRequest deletePhotoRequest) {
        log.debug("Creating new photo from dto: {}", deletePhotoRequest);
        productPhotoService.deletePreviewPhoto(deletePhotoRequest);
    }

    @GetMapping("/default/{defaultPhotoName}")
    public ResponseEntity<FileSystemResource> getDefaultPhoto(@PathVariable String defaultPhotoName) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new FileSystemResource(productPhotoService.getDefaultProductPhoto(defaultPhotoName)));
    }

}
