package com.example.userservice.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductPhotoRequest {
    private String productId;
    private String productColor;
}
