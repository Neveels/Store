package com.example.userservice.service.impl;

import com.example.userservice.aws.service.PhotoStorageService;
import com.example.userservice.dto.mapper.ProductMapper;
import com.example.userservice.dto.request.ProductRequest;
import com.example.userservice.dto.response.ProductResponse;
import com.example.userservice.dto.response.ProductSearchResponse;
import com.example.userservice.entity.enums.PRODUCT_TYPE;
import com.example.userservice.entity.mongo.Color;
import com.example.userservice.entity.mongo.Product;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.FavoriteRepository;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.userservice.constants.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final PhotoStorageService photoStorageService;
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper,
                              ProductRepository productRepository,
                              PhotoStorageService photoStorageService,
                              FavoriteRepository favoriteRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.photoStorageService = photoStorageService;
        this.favoriteRepository = favoriteRepository;
    }


    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        if (productRepository.findByName(productRequest.getName())
                .isEmpty()) {
            return productMapper.toResponseDto(productRepository
                    .save(productMapper
                            .toProduct(productRequest)));
        } else {
            throw new BusinessException(String.format(PRODUCT_WITH_NAME_ALREADY_EXIST, productRequest.getName()), HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    @Override
    public void deleteProduct(String id) {
        Product product = getProduct(id);
        product.getColors()
                .forEach(color -> {
                    List<String> photos = color.getPhotos();
                    if (Objects.nonNull(photos)) {
                        photos
                                .forEach(photoStorageService::deleteFile);
                    }
                });
        productRepository.delete(getProduct(id));
        favoriteRepository.deleteAllByProductId(id);
    }

    @Override
    public List<Product> getProductByType(PRODUCT_TYPE productTypeEnum) {
        return productRepository
                .findAll()
                .stream()
                .filter(product -> Objects.equals(product.getType(), productTypeEnum))
                .toList();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository
                .findByName(name)
                .orElseThrow(
                        () -> new BusinessException(String.format(PRODUCT_WITH_NAME_NOT_FOUND, name), HttpStatus.NOT_FOUND)
                );
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest) {
        productRepository.findById(productRequest.getId())
                .ifPresentOrElse(product -> {
                            List<Color> newColorList = product.getColors()
                                    .stream()
                                    .filter(color -> productRequest.getColors()
                                            .stream()
                                            .noneMatch(singleColor -> color.getName()
                                                    .equals(singleColor.getName())))
                                    .toList();
                            newColorList.forEach(color -> {
                                List<String> photos = color.getPhotos();
                                if (!Objects.isNull(photos)) {
                                    photos.forEach(photoStorageService::deleteFile);
                                }
                            });
                            Product productAfterMapping = productMapper.toProduct(productRequest);
                            productAfterMapping.setPreview(product.getPreview());
                            productRepository.save(productAfterMapping);
                        },
                        () -> {
                            throw new BusinessException(String.format(PRODUCT_WITH_ID_NOT_FOUND, productRequest.getId()), HttpStatus.NOT_FOUND);
                        });
        return productMapper.toResponseDto(getProduct(productRequest.getId()));
    }

    @Override
    public List<ProductSearchResponse> getProductsViaSearch(String searchString) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getName()
                        .contains(searchString.toLowerCase()))
                .map(product -> ProductSearchResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .type(product.getType())
                        .build())
                .toList();
    }

    private Product getProduct(String productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new BusinessException(PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

}
