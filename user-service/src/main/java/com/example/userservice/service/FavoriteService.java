package com.example.userservice.service;

import com.example.userservice.dto.request.FavoriteRequest;
import com.example.userservice.dto.response.FavoriteResponse;

import java.util.List;

public interface FavoriteService {
    FavoriteResponse addProductToFavorite(FavoriteRequest favoriteRequest);
    List<FavoriteResponse> getAllUserFavorite();
    void deleteFromFavoriteById(Integer favoriteId);
}
