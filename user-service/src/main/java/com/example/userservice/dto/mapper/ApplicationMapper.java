package com.example.userservice.dto.mapper;

import com.example.userservice.dto.request.ApplicationRequest;
import com.example.userservice.dto.response.ApplicationResponse;
import com.example.userservice.entity.Application;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ApplicationMapper {
    public ApplicationResponse toResponseDto(Application application) {
        return ApplicationResponse.builder()
                .id(application.getId())
                .firstName(application.getFirstName())
                .lastName(application.getLastName())
                .email(application.getEmail())
                .phone(application.getPhone())
                .message(application.getMessage())
                .role(application.getRole())
                .build();
    }

    public Application toApplication(ApplicationRequest applicationRequest) {
        return Application.builder()
                .firstName(applicationRequest.getFirstName())
                .lastName(applicationRequest.getLastName())
                .email(applicationRequest.getEmail())
                .phone(applicationRequest.getPhone())
                .message(applicationRequest.getMessage())
                .role(applicationRequest.getRole())
                .build();
    }
}
