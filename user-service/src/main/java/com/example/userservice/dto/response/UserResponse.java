package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.USER_ROLE;
import lombok.Builder;

@Builder
public record UserResponse(
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        USER_ROLE role,
        String avatar
) {
}
