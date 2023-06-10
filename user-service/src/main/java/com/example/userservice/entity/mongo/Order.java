package com.example.userservice.entity.mongo;

import com.example.userservice.entity.enums.ORDER_STATUS;
import com.example.userservice.entity.enums.PAYMENT_TYPE;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document()
@ToString
public class Order {
    @MongoId
    private String id;
    private Long userId;
    private String address;
    private PAYMENT_TYPE paymentOption;
    private LocalDate date;
    private LocalDate dateDone;
    private ORDER_STATUS status;
    private Double finalPrice;
    private List<OrderProduct> products;

}
