package com.example.userservice.service;

import com.example.userservice.dto.request.DeletePhotoRequest;
import com.example.userservice.dto.request.ProductPhotoRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ProductPhotoService {
    File getProductPhoto(String productId, String photoName);
    String addProductPhoto(MultipartFile multipartFile, ProductPhotoRequest productPhotoRequest);
    void deleteProductPhoto(DeletePhotoRequest deletePhotoRequest);
    File getDefaultProductPhoto(String defaultPhotoName);

    String addPreviewPhoto(MultipartFile file, ProductPhotoRequest productPhotoRequest);

    void deletePreviewPhoto(DeletePhotoRequest deletePhotoRequest);

    File getPreviewProductPhoto(String productId, String photoName);
}
