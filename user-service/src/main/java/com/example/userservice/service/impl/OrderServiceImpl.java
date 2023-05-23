package com.example.userservice.service.impl;

import com.example.userservice.dto.mapper.CartMapper;
import com.example.userservice.dto.mapper.OrderMapper;
import com.example.userservice.dto.request.OrderRequest;
import com.example.userservice.dto.response.OrderResponse;
import com.example.userservice.entity.User;
import com.example.userservice.entity.enums.OrderStatus;
import com.example.userservice.entity.mongo.Cart;
import com.example.userservice.entity.mongo.Order;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.CartRepository;
import com.example.userservice.repository.OrderRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.OrderService;
import com.example.userservice.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.example.userservice.constants.Constants.CART_IS_EMPTY_MESSAGE;
import static com.example.userservice.constants.Constants.ORDER_NOT_FOUND;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserUtils userUtils;
    private final CartMapper cartMapper;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, UserUtils userUtils, CartMapper cartMapper, CartRepository cartRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userUtils = userUtils;
        this.cartMapper = cartMapper;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) {
        User user = userUtils.getUser();
        List<Cart> carts = cartRepository.findAllByUserId(user.getId());
        if (carts.isEmpty()) {
            throw new BusinessException(CART_IS_EMPTY_MESSAGE,
                    HttpStatus.FORBIDDEN);
        }
        carts.forEach(cart -> cartRepository.deleteById(cart.getId()));
        Order order = orderMapper.toOrder(orderRequest, user.getId());
        order.setStatus(OrderStatus.PROCESSING);
        return orderMapper.toResponseDto(orderRepository.save(order), null, null, null);
    }

    @Override
    public OrderResponse changeOrderStatus(String id, OrderStatus orderStatus) {
        Order order = getOrder(id);
        User user = userRepository.findById(order.getUserId()).get();

        if (orderStatus.equals(OrderStatus.COMPLETED)) {
            List<Cart> carts = order.getProducts()
                    .stream()
                    .map(cart -> cartMapper.toCart(cart.getProduct(), order.getUserId()))
                    .toList();
            cartRepository.deleteAll(carts);
            order.setStatus(orderStatus);
            order.setDateDone(LocalDate.now());
        } else {
            order.setStatus(orderStatus);
        }
        return orderMapper.toResponseDto(orderRepository.save(order), "%s %s".formatted(user.getFirstName(), user.getLastName()),
                user.getEmail(),
                user.getPhoneNumber());
    }

    @Override
    public List<OrderResponse> getAllUserOrder() {
        User user = userUtils.getUser();
        List<Order> list = orderRepository.findByUserId(user.getId());
        Collections.reverse(list);
        return list
                .stream()
                .map(order -> orderMapper.toResponseDto(order, "%s %s".formatted(user.getFirstName(), user.getLastName()),
                        user.getEmail(),
                        user.getPhoneNumber()))
                .toList();
    }

    @Override
    public List<OrderResponse> getAllByOrderStatus(OrderStatus orderStatus) {
        List<Order> list = orderRepository.findAllByStatus(orderStatus);
        Collections.reverse(list);
        return list
                .stream()
                .map(order -> {
                    User user = userRepository.findById(order.getUserId()).get();
                    return orderMapper.toResponseDto(order,
                            "%s %s".formatted(user.getFirstName(), user.getLastName()),
                            user.getEmail(),
                            user.getPhoneNumber());
                })
                .toList();
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> {
                    User user = userRepository.findById(order.getUserId()).get();
                    return orderMapper.toResponseDto(order,
                            "%s %s".formatted(user.getFirstName(), user.getLastName()),
                            user.getEmail(),
                            user.getPhoneNumber());
                })
                .toList();
    }


    private Order getOrder(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ORDER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
