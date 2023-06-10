package com.example.userservice.service.impl;

import com.example.userservice.dto.response.CardResponse;
import com.example.userservice.entity.Card;
import com.example.userservice.entity.User;
import com.example.userservice.repository.CardRepository;
import com.example.userservice.utils.UserUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/7/2023 4:42 PM
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {

    @InjectMocks
    private CardServiceImpl cardService;
    @Mock
    private CardRepository cardRepository;
    @Mock
    private UserUtils userUtils;

    private static List<Card> cardList;
    private static List<CardResponse> cardResponseList;
    private static User user;

    @BeforeAll
    static void setUp() {
        user = User.builder()
                .id(1L)
                .email("neevels@mail.ru")
                .firstName("Ilya")
                .lastName("Ignatovich")
                .build();

        cardList = List.of(
                Card.builder()
                        .id(1)
                        .user(user)
                        .number("qweqwe")
                        .owner("aqeqw")
                        .status(false)
                        .build(),
                Card.builder()
                        .id(1)
                        .user(user)
                        .number("dasas")
                        .owner("asdasdasd")
                        .status(false)
                        .build()
        );

        cardResponseList = List.of(
                CardResponse.builder()
                        .id(1)
                        .number("qweqwe")
                        .owner("aqeqw")
                        .status(false)
                        .build(),
                CardResponse.builder()
                        .id(1)
                        .number("dasas")
                        .owner("asdasdasd")
                        .status(false)
                        .build()
        );
    }

    @Test
    void createCard() {
    }

    @Test
    void updateCard() {
    }

    @Test
    void getAllByUserId() {
        when(userUtils.getUser()).thenReturn(user);
        when(cardRepository.findAllByUserId(user.getId())).thenReturn(cardList);
        List<CardResponse> cardResponses = cardService.getAllByUserId();
        assertEquals(cardResponses, cardResponseList);

    }

    @Test
    void deleteById() {
    }

    @Test
    void changeActiveCard() {
    }
}