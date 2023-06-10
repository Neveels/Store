package com.example.userservice.constants;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Constants {
     public static final String AMAZON_MESSAGE = "Problem in Amazon Access - ";
     public static final String FILE_DOES_NOT_EXIST = "The file does not exist";
     public static final String USER_WITH_EMAIL_NOT_FOUND = "User with email: %s not found";
     public static final String USER_WITH_EMAIL_ALREADY_EXIST = "User with email %s already exist";
     public static final String SAME_PASSWORD_EXCEPTION = "Old and new password are the same. Please, try again!";
     public static final String INCORRECT_PASSWORD_EXCEPTION = "Old password doesn't correct. Please, try again!";
     public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
     public static final String TOKEN_PREFIX = "Bearer ";
     public static final String ADDRESS_WITH_ID_NOT_FOUND = "Address with id %d not found";
     public static final String ACTIVE_ADDRESS_WITH_ID_NOT_FOUND = "Active address doesn't found";
     public static final String APPLICATION_NOT_FOUND = "Application not found";
     public static final String CARD_WITH_ID_NOT_FOUND = "Card with id: %d not found";
     public static final String ACTIVE_CARD_WITH_ID_NOT_FOUND = "Active card doesn't found";
     public static final String ITEM_AT_CART_NOT_FOUND = "Item at cart doesnt found";
     public static final String ORDER_NOT_FOUND = "Order not found";
     public static final String CART_IS_EMPTY_MESSAGE ="Your cart is empty! If you want to make an order, try to fill your cart!";
     public static final String PRODUCT_NOT_FOUND = "Product not found";
     public static final String PRODUCT_WITH_NAME_NOT_FOUND = "Product with name %s not found";
     public static final String PRODUCT_IN_CART_NOT_FOUND = "Product in cart doesnt found";
     public static final String PRODUCT_WITH_ID_NOT_FOUND = "Product with id: %s not found";
     public static final String PRODUCT_WITH_COLOR_NOT_FOUND ="Product with color: %s not found";
     public static final String PRODUCT_WITH_NAME_ALREADY_EXIST = "Product with name %s already exist";
     public static final String DELETE_PHOTO_MESSAGE = "You cannot delete default user photo";
}
