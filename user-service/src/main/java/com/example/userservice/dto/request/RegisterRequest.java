package com.example.userservice.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequest {

    private String firstName;
    private String lastName;
    @Email(message = "Email already exists")
    private String email;
    private String password;
}