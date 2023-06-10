package com.example.userservice.dto.response;

import com.example.userservice.entity.enums.USER_ROLE;
import lombok.Builder;

@Builder
public record ApplicationResponse(
         Integer id,
         String firstName,
         String lastName,
         String phone,
         String email,
         String message,
         USER_ROLE role
){
}
