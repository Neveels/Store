package com.example.userservice.service.impl;

import com.example.userservice.aws.enums.Path;
import com.example.userservice.aws.service.PhotoStorageService;
import com.example.userservice.dto.request.DeletePhotoRequest;
import com.example.userservice.dto.request.ProductPhotoRequest;
import com.example.userservice.entity.mongo.Product;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.service.ProductPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/19/2023 11:43 AM
 */
@Slf4j
@Service
public class ProductPhotoServiceImpl implements ProductPhotoService {

    private final ProductRepository productRepository;
    private final PhotoStorageService photoStorageService;

    @Autowired
    public ProductPhotoServiceImpl(ProductRepository productRepository, PhotoStorageService photoStorageService) {
        this.productRepository = productRepository;
        this.photoStorageService = photoStorageService;
    }


    @Override
    public File getProductPhoto(String path, String name, String color, String photo) {
        return photoStorageService.getFile("%s/%s/%s/".formatted(path, name, color), photo);
    }

    @Override
    public String addProductPhoto(MultipartFile multipartFile, ProductPhotoRequest productPhotoRequest) {
        Product product = getProduct(productPhotoRequest.getProductId());
        UUID uuid = UUID.randomUUID();
        String path = "%s/%s/%s/%s.jpg".formatted(product.getType(), product.getName(), productPhotoRequest.getProductColor(), uuid);
        log.info(product.toString());
        product.getColors()
                .stream()
                .filter(color -> Objects.equals(color.getName(), productPhotoRequest.getProductColor()))
                .findFirst()
                .ifPresentOrElse(color -> {
                            List<String> photoList;
                            if (Objects.isNull(color.getPhotos())) {
                                photoList = new ArrayList<>();
                            } else {
                                photoList = color.getPhotos();
                            }
                            photoList.add(path);
                            color.setPhotos(photoList);
                            productRepository.save(product);
                        },
                        () -> {
                            throw new BusinessException(String.format("Product with color: %s not found", productPhotoRequest.getProductColor()), HttpStatus.NOT_FOUND);
                        });

        return photoStorageService.uploadFile(path, multipartFile);
    }


    @Override
    public void deleteProductPhoto(DeletePhotoRequest deletePhotoRequest) {
        String photo = deletePhotoRequest.getPhotoPath();
        Product product = getProduct(deletePhotoRequest.getProductId());
        product
                .getColors()
                .forEach(color -> color.getPhotos()
                        .stream()
                        .filter(photoPath -> photoPath.equals(deletePhotoRequest.getPhotoPath()))
                        .findAny()
                        .ifPresentOrElse(path -> {
                                    List<String> photos = color.getPhotos();
                                    photos.remove(path);
                                    color.setPhotos(photos);
                                    productRepository.save(product);
                                }
                                ,
                                () -> {
                                    throw new BusinessException(String.format("Path: %s not found", photo), HttpStatus.NOT_FOUND);
                                }));
        photoStorageService.deleteFile(photo);

    }

    @Override
    public File getDefaultProductPhoto(String defaultPhotoName) {
        return photoStorageService.getFile("default/", "%s.png".formatted(defaultPhotoName));
    }


    private Product getProduct(String productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new BusinessException
                        (String.format("Product with id: %s not found", productId),
                                HttpStatus.NOT_FOUND));
    }
}
