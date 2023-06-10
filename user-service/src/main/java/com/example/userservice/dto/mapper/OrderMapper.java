package com.example.userservice.dto.mapper;

import com.example.userservice.dto.request.OrderRequest;
import com.example.userservice.dto.response.OrderResponse;
import com.example.userservice.entity.mongo.Order;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
public class OrderMapper {
    public OrderResponse toResponseDto(Order order, String fullName, String email, String phoneNumber) {
        return OrderResponse.builder()
                .address(order.getAddress())
                .dateDone(order.getDateDone())
                .products(order.getProducts())
                .paymentOption(order.getPaymentOption())
                .id(order.getId())
                .finalPrice(order.getFinalPrice())
                .status(order.getStatus())
                .date(order.getDate())
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }

    public Order toOrder(OrderRequest orderRequest, Long userId) {
        return Order.builder()
                .address(orderRequest.getAddress())
                .userId(userId)
                .finalPrice(orderRequest.getFinalPrice())
                .products(orderRequest.getProducts())
                .paymentOption(orderRequest.getPaymentOption())
                .date(LocalDate.now())
                .build();
    }
}
