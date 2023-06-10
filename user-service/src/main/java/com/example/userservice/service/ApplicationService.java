package com.example.userservice.service;

import com.example.userservice.dto.request.ApplicationRequest;
import com.example.userservice.dto.response.ApplicationResponse;
import com.example.userservice.dto.response.UserResponse;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse addApplication(ApplicationRequest applicationRequest);

    List<ApplicationResponse> getAllApplications();

    void deleteById(Integer id);

    UserResponse updateToUser(Integer id);
}
