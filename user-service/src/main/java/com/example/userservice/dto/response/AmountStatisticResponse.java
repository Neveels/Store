package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.PRODUCT_TYPE;
import lombok.Builder;

@Builder
public record AmountStatisticResponse(
        PRODUCT_TYPE key,
        Integer value
) {

}
