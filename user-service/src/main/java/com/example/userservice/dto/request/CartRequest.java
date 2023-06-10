package com.example.userservice.dto.request;

import com.example.userservice.entity.enums.PRODUCT_TYPE;
import com.example.userservice.entity.mongo.Configuration;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartRequest {
    private String id;
    private String name;
    private String preview;
    private PRODUCT_TYPE type;
    private Double price;
    private String color;
    private List<Configuration> configurations;
}
