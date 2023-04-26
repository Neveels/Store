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

/**
 * @author Neevels
 * @version 1.0
 * @date 4/26/2023 10:16 PM
 */
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
                .orElseThrow(() -> new BusinessException("Product not found", HttpStatus.NOT_FOUND));

        Favorite favorite = Favorite.builder()
                .user(user)
                .productId(productId)
                .build();
        Favorite savedFavorite = favoriteRepository.save(favorite);

        return FavoriteResponse.builder()
                .favoriteId(savedFavorite.getId())
                .preview(product.getPreview())
                .productName(product.getName())
                .price(product.getPrice())
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
                            .orElseThrow(() -> new BusinessException("Product not found", HttpStatus.NOT_FOUND));
                    return FavoriteResponse
                            .builder()
                            .favoriteId(favorite.getId())
                            .preview(product.getPreview())
                            .price(product.getPrice())
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
