package com.example.userservice.dto.response;

import lombok.Builder;

@Builder
public record CardResponse(
        Integer id,
        String number,
        String validityDate,
        String owner,
        Boolean status
) {
}
