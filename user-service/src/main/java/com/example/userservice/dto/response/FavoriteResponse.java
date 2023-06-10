package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.PRODUCT_TYPE;
import lombok.Builder;

@Builder
public record FavoriteResponse (
        Integer id,
        String productId,
        String productName,
        String productPreview,
        PRODUCT_TYPE productType
) {
}
