package com.example.userservice.controller;

import com.example.userservice.dto.request.CardRequest;
import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody CardRequest cardRequest) {
        return ResponseEntity
                .ok()
                .body(cardService.createCard(cardRequest));
    }


    @PutMapping
    public ResponseEntity<CardResponse> updateCard(@RequestBody CardRequest cardRequest) {
        return ResponseEntity
                .ok()
                .body(cardService.updateCard(cardRequest));
    }


    @GetMapping
    public ResponseEntity<List<CardResponse>> getAllByUserId() {
        return ResponseEntity
                .ok()
                .body(cardService.getAllByUserId());

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Integer id) {
        cardService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}/setActive")
    public ResponseEntity<List<CardResponse>> changeAddressStatusForMain(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(cardService.changeActiveCard(id));
    }

}
