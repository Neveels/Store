package com.example.userservice.dto.mapper;

import com.example.userservice.aws.enums.PHOTO_PATH;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.Application;
import com.example.userservice.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse toResponseDto(User user) {
        return UserResponse.builder()
                .avatar(user.getAvatar())
                .role(user.getRole())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public User toUser(Application application, String password) {
        return User.builder()
                .firstName(application.getFirstName())
                .lastName(application.getLastName())
                .email(application.getEmail())
                .role(application.getRole())
                .phoneNumber(application.getPhone())
                .avatar(PHOTO_PATH.DEFAULT_PATH.getUrl())
                .password(passwordEncoder.encode(password))
                .build();
    }
}
