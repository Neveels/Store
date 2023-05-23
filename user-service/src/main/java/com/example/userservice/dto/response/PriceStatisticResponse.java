package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.ProductType;
import lombok.Builder;

@Builder
public record PriceStatisticResponse(
        ProductType key,
        Double value
) {

}
