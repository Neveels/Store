package com.example.userservice.controller;

import com.example.userservice.dto.request.OrderRequest;
import com.example.userservice.dto.response.AmountStatisticResponse;
import com.example.userservice.dto.response.OrderResponse;
import com.example.userservice.entity.enums.ORDER_STATUS;
import com.example.userservice.service.OrderService;
import com.example.userservice.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final StatisticService statisticService;

    public OrderController(OrderService orderService, StatisticService statisticService) {
        this.orderService = orderService;
        this.statisticService = statisticService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Adding {} dto to order", orderRequest);
        return ResponseEntity
                .ok()
                .body(orderService.addOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllUserOrder() {
        log.info("Get all user orders");
        return ResponseEntity
                .ok()
                .body(orderService.getAllUserOrder());
    }

    @GetMapping("/{orderStatus}")
    public ResponseEntity<List<OrderResponse>> getAllByOrderStatus(@PathVariable ORDER_STATUS orderStatus) {
        log.info("Get all by {} status", orderStatus);
        return ResponseEntity
                .ok()
                .body(orderService.getAllByOrderStatus(orderStatus));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponse>> getAll() {
        log.info("Get all by");
        return ResponseEntity
                .ok()
                .body(orderService.getAllOrders());
    }

    @GetMapping("/amountStatistics")
    public ResponseEntity<List<AmountStatisticResponse>> getAmountStatistics() {
        log.info("Get all by");
        return ResponseEntity
                .ok()
                .body(statisticService.getProfitByProductType());
    }

    @PutMapping("/{id}/{orderStatus}")
    public ResponseEntity<OrderResponse> changeOrderStatus(@PathVariable String id,
                                                           @PathVariable ORDER_STATUS orderStatus) {
        log.info("Change order status with id {}", id);
        return ResponseEntity
                .ok()
                .body(orderService.changeOrderStatus(id, orderStatus));
    }

}
