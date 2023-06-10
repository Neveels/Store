package com.example.userservice.service.impl;

import com.example.userservice.dto.response.CartResponse;
import com.example.userservice.entity.User;
import com.example.userservice.entity.mongo.Cart;
import com.example.userservice.repository.CartRepository;
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
 * @date 5/4/2023 3:23 PM
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @InjectMocks
    private CartServiceImpl cartService;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private UserUtils userUtils;


    private static User user;

    private static List<CartResponse> cartResponseList;
    private static List<Cart> cartList;

    @BeforeAll
    static void setUp() {
        user = User.builder()
                .id(1L)
                .firstName("Ilya")
                .lastName("Ignatovich")
                .email("111@mail.ru")
                .build();

        cartResponseList = List.of(
                CartResponse.builder()
                        .id("123")
                        .price(123.3)
                        .name("Iphone")
                        .build(),
                CartResponse.builder()
                        .id("1235")
                        .price(123.3)
                        .name("Iphone12")
                        .build()
        );
        cartList = List.of(
                Cart.builder()
                        .id("123")
                        .price(123.3)
                        .name("Iphone")
                        .userId(1L)
                        .build(),
                Cart.builder()
                        .id("1235")
                        .userId(1L)
                        .price(123.3)
                        .name("Iphone12")
                        .build()
        );

    }

    @Test
    void addToCart() {


    }

    @Test
    void getAllUserCart() {
//        when(userUtils.getUser()).thenReturn(user);
        when(cartRepository.findAllByUserId(1L)).thenReturn(cartList);
        List<CartResponse> allUserCart = cartService.getAllUserCart();
        assertEquals(allUserCart, cartResponseList);
    }

    @Test
    void deleteFromCart() {
    }

    @Test
    void updateCart() {
    }
}