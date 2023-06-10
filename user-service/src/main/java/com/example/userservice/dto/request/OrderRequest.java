package com.example.userservice.dto.request;

import com.example.userservice.entity.enums.PAYMENT_TYPE;
import com.example.userservice.entity.mongo.OrderProduct;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequest {
    private String address;
    private PAYMENT_TYPE paymentOption;
    private Double finalPrice;
    private List<OrderProduct> products;
}
