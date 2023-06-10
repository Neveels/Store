package com.example.userservice.controller;

import com.example.userservice.dto.request.FavoriteRequest;
import com.example.userservice.dto.response.FavoriteResponse;
import com.example.userservice.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity<FavoriteResponse> addToFavorite(@RequestBody FavoriteRequest favoriteRequest) {
        return ResponseEntity
                .ok()
                .body(favoriteService.addProductToFavorite(favoriteRequest));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteResponse>> getAll() {
        return ResponseEntity
                .ok()
                .body(favoriteService.getAllUserFavorite());
    }

    @DeleteMapping("/{favoriteId}")
    public void deleteFromFavorite(@PathVariable Integer favoriteId) {
        favoriteService.deleteFromFavoriteById(favoriteId);
    }
}
