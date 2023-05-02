package com.example.userservice.dto.mapper;

import com.example.userservice.dto.request.OrderRequest;
import com.example.userservice.dto.response.OrderResponse;
import com.example.userservice.entity.mongo.Order;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Neevels
 * @version 1.0
 * @date 5/2/2023 9:45 PM
 */
@Component
@NoArgsConstructor
public class OrderMapper {
    public OrderResponse toResponseDto(Order order) {
        return OrderResponse.builder()
                .address(order.getAddress())
                .dateDone(order.getDateDone())
                .products(order.getProducts())
                .paymentOption(order.getPaymentOption())
                .id(order.getId())
                .finalPrice(order.getFinalPrice())
                .status(order.getStatus())
                .date(order.getDate())
                .build();
    }

    public Order toOrder(OrderRequest orderRequest, Long userId) {
        return Order.builder()
                .id(orderRequest.getId())
                .address(orderRequest.getAddress())
                .userId(userId)
                .finalPrice(orderRequest.getFinalPrice())
                .products(orderRequest.getProducts())
                .paymentOption(orderRequest.getPaymentOption())
                .status(orderRequest.getStatus() )
                .build();
    }
}
