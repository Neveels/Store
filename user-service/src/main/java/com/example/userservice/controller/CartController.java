package com.example.userservice.controller;

import com.example.userservice.dto.request.CartRequest;
import com.example.userservice.dto.response.CartResponse;
import com.example.userservice.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest cartRequest) {
        log.info("Adding {} dto to cart", cartRequest);
        return ResponseEntity
                .ok()
                .body(cartService.addToCart(cartRequest));
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> getAllFromCart() {
        log.info("Getting all from cart");
        return ResponseEntity
                .ok()
                .body(cartService.getAllUserCart());
    }

    @PutMapping
    public ResponseEntity<CartResponse> update(@RequestBody CartRequest cartRequest) {
        log.info("Update {} dto in cart", cartRequest);
        return ResponseEntity
                .ok()
                .body(cartService.updateCart(cartRequest));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable String id) {
        log.info("Remove item from cart with id {}", id);
        cartService.deleteFromCart(id);
    }

}
