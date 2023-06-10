package com.example.userservice.service;

import com.example.userservice.dto.request.OrderRequest;
import com.example.userservice.dto.response.OrderResponse;
import com.example.userservice.entity.enums.ORDER_STATUS;

import java.util.List;

public interface OrderService {
    OrderResponse addOrder(OrderRequest orderRequest);
    OrderResponse changeOrderStatus(String id, ORDER_STATUS orderStatus);
    List<OrderResponse> getAllUserOrder();
    List<OrderResponse> getAllByOrderStatus(ORDER_STATUS orderStatus);

    List<OrderResponse> getAllOrders();
}
