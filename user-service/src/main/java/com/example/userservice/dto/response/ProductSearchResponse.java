package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.PRODUCT_TYPE;
import lombok.Builder;

@Builder
public record ProductSearchResponse (
        String id,
        String name,
        PRODUCT_TYPE type
) {
}
