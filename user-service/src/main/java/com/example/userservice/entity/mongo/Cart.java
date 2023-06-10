package com.example.userservice.entity.mongo;

import com.example.userservice.entity.enums.PRODUCT_TYPE;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document()
@ToString
public class Cart {
    @MongoId
    private String id;
    private Long userId;
    private String name;
    private String preview;
    private Double price;
    private String color;
    private PRODUCT_TYPE type;
    private List<Configuration> configurations;
}
