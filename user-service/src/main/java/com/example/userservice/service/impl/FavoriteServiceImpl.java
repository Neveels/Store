package com.example.userservice.service.impl;

import com.example.userservice.dto.request.FavoriteRequest;
import com.example.userservice.dto.response.FavoriteResponse;
import com.example.userservice.entity.Favorite;
import com.example.userservice.entity.User;
import com.example.userservice.entity.mongo.Product;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.FavoriteRepository;
import com.example.userservice.repository.ProductRepository;
import com.example.userservice.service.FavoriteService;
import com.example.userservice.utils.UserUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.userservice.constants.Constants.PRODUCT_NOT_FOUND;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserUtils userUtils;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, ProductRepository productRepository, UserUtils userUtils) {
        this.favoriteRepository = favoriteRepository;
        this.productRepository = productRepository;
        this.userUtils = userUtils;
    }

    @Transactional
    @Override
    public FavoriteResponse addProductToFavorite(FavoriteRequest favoriteRequest) {
        User user = userUtils.getUser();
        String productId = favoriteRequest.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND));

        Favorite favorite = Favorite.builder()
                .user(user)
                .productId(productId)
                .build();
        Favorite favoriteFromDb = favoriteRepository.save(favorite);

        return FavoriteResponse.builder()
                .id(favoriteFromDb.getId())
                .productType(product.getType())
                .productId(productId)
                .productPreview(product.getPreview())
                .productName(product.getName())
                .build();
    }

    @Override
    public List<FavoriteResponse> getAllUserFavorite() {
        var user = userUtils.getUser();
        return favoriteRepository.findAllByUserId(user.getId())
                .stream()
                .map(favorite -> {
                    String productId = favorite.getProductId();
                    var product = productRepository.findById(productId)
                            .orElseThrow(() -> new BusinessException(PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND));
                    return FavoriteResponse.builder()
                            .id(favorite.getId())
                            .productType(product.getType())
                            .productId(productId)
                            .productPreview(product.getPreview())
                            .productName(product.getName())
                            .build();
                })
                .toList();
    }

    @Transactional
    @Override
    public void deleteFromFavoriteById(Integer favoriteId) {
        var user = userUtils.getUser();
        favoriteRepository.deleteByIdAndUser(favoriteId, user);
    }
}
