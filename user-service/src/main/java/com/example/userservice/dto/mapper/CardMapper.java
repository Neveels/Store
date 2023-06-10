package com.example.userservice.dto.mapper;

import com.example.userservice.dto.request.CardRequest;
import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Card;
import com.example.userservice.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CardMapper {
    public CardResponse toResponseDto(Card card) {
        return CardResponse.builder()
                .id(card.getId())
                .validityDate(card.getValidityDate())
                .number(card.getNumber())
                .owner(card.getOwner())
                .status(card.getStatus())
                .build();
    }

    public Card toCard(CardRequest cardRequest, User user) {
        return Card.builder()
                .id(cardRequest.getId())
                .validityDate(cardRequest.getValidityDate())
                .number(cardRequest.getNumber())
                .owner(cardRequest.getOwner())
                .user(user)
                .status(cardRequest.getStatus())
                .build();
    }
}
