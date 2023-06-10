package com.example.userservice.service.impl;

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

import static com.example.userservice.constants.Constants.PRODUCT_WITH_COLOR_NOT_FOUND;
import static com.example.userservice.constants.Constants.PRODUCT_WITH_ID_NOT_FOUND;

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
    public File getProductPhoto(String productId, String photoName) {
        System.out.println(productId.formatted("%s/") + photoName);
        return photoStorageService.getFile("%s/".formatted(productId), photoName);
    }

    @Override
    public String addProductPhoto(MultipartFile multipartFile, ProductPhotoRequest productPhotoRequest) {
        Product product = getProduct(productPhotoRequest.getProductId());
        UUID uuid = UUID.randomUUID();
        String path = "%s/%s.jpg".formatted(productPhotoRequest.getProductId(), uuid);
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
                            throw new BusinessException(String.format(PRODUCT_WITH_COLOR_NOT_FOUND, productPhotoRequest.getProductColor()), HttpStatus.NOT_FOUND);
                        });

        return photoStorageService.uploadFile(path, multipartFile);
    }

    @Override
    public void deleteProductPhoto(DeletePhotoRequest deletePhotoRequest) {
        String photo = "%s/%s".formatted(deletePhotoRequest.getProductId(), deletePhotoRequest.getPhotoPath());
        Product product = getProduct(deletePhotoRequest.getProductId());
        product
                .getColors()
                .forEach(color -> {
                    color.getPhotos()
                            .stream()
                            .filter(photoPath -> photoPath.equals("%s/%s".formatted(deletePhotoRequest.getProductId(), deletePhotoRequest.getPhotoPath())))
                            .findAny()
                            .ifPresent(path -> {
                                List<String> photos = color.getPhotos();
                                photos.remove(path);
                                color.setPhotos(photos);
                                productRepository.save(product);
                            });
                });
        photoStorageService.deleteFile(photo);
    }

    @Override
    public File getDefaultProductPhoto(String defaultPhotoName) {
        return photoStorageService.getFile("default/", "%s.png".formatted(defaultPhotoName));
    }

    @Override
    public File getPreviewProductPhoto(String productId, String photoName) {
        return photoStorageService.getFile("%s/".formatted(productId), photoName);

    }


    @Override
    public String addPreviewPhoto(MultipartFile file, ProductPhotoRequest productPhotoRequest) {
        Product product = getProduct(productPhotoRequest.getProductId());
        UUID uuid = UUID.randomUUID();
        String path = "%s.jpg".formatted(uuid);
        product.setPreview(path);
        productRepository.save(product);
        photoStorageService.uploadFile("%s/%s".formatted(productPhotoRequest.getProductId(), path), file);
        return path;
    }

    @Override
    public void deletePreviewPhoto(DeletePhotoRequest deletePhotoRequest) {
        Product product = getProduct(deletePhotoRequest.getProductId());
        product.setPreview(null);
        productRepository.save(product);
        photoStorageService.deleteFile("%s/%s".formatted(deletePhotoRequest.getProductId(), deletePhotoRequest.getPhotoPath()));
    }


    private Product getProduct(String productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new BusinessException
                        (String.format(PRODUCT_WITH_ID_NOT_FOUND, productId),
                                HttpStatus.NOT_FOUND));
    }

}
