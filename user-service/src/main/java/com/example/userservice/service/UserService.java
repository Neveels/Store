package com.example.userservice.service;


import com.example.userservice.dto.request.UserRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.User;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 2/13/2023 10:45 AM
 */
public interface UserService {
    List<UserResponse> getAllUsers();


    UserResponse getUser();

    UserResponse updateUser(UserRequest userRequest);
}
