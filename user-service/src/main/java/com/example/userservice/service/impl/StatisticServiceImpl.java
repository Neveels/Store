package com.example.userservice.service.impl;

import com.example.userservice.dto.response.AmountStatisticResponse;
import com.example.userservice.dto.response.PriceStatisticResponse;
import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.entity.enums.ProductType;
import com.example.userservice.entity.mongo.Order;
import com.example.userservice.entity.mongo.OrderProduct;
import com.example.userservice.repository.OrderRepository;
import com.example.userservice.service.StatisticService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final OrderRepository orderRepository;

    public StatisticServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<AmountStatisticResponse> getProfitByProductType() {
        List<Order> allByStatus = orderRepository.findAllByStatus(OrderStatus.COMPLETED);
        return Stream.of(ProductType.values())
                .collect(Collectors.toMap(
                        value -> value,
                        value -> (int) allByStatus.stream()
                                .map(order -> order.getProducts()
                                        .stream()
                                        .filter(orderProduct -> Objects.equals(orderProduct.getType(), value))
                                        .mapToInt(OrderProduct::getAmount)
                                        .sum()
                                )
                                .count()))
                .entrySet()
                .stream()
                .map(map -> AmountStatisticResponse.builder()
                        .key(map.getKey())
                        .value(map.getValue())
                        .build())
                .toList();
    }

    @Override
    public List<PriceStatisticResponse> getPriceStatistic() {
        List<Order> allByStatus = orderRepository.findAllByStatus(OrderStatus.COMPLETED);
        return List.of(ProductType.values())
                .stream()
                .collect(Collectors.toMap(
                        value -> value,
                        value -> allByStatus.stream()
                                .filter(order -> Objects.equals(order.getStatus(), value))
                                .mapToDouble(Order::getFinalPrice)
                                .sum()
                ))
                .entrySet()
                .stream()
                .map(map -> PriceStatisticResponse.builder()
                        .key(map.getKey())
                        .value(map.getValue())
                        .build())
                .toList();
    }
}
