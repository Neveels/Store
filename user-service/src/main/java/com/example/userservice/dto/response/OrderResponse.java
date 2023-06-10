package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.ORDER_STATUS;
import com.example.userservice.entity.enums.PAYMENT_TYPE;
import com.example.userservice.entity.mongo.OrderProduct;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record OrderResponse(
        String id,
        String address,
        PAYMENT_TYPE paymentOption,
        LocalDate date,
        LocalDate dateDone,
        ORDER_STATUS status,
        Double finalPrice,
        List<OrderProduct> products,
        String fullName,
        String phoneNumber,
        String email
        ) {
}
