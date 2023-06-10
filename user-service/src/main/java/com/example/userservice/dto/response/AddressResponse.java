package com.example.userservice.dto.response;

import lombok.Builder;

@Builder
public record AddressResponse(
        Integer id,
        String country,
        String state,
        String city,
        String street,
        String house,
        String apartment,
        Boolean status
) {
}
