package com.example.userservice.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token,
        UserResponse userResponse
) {
}