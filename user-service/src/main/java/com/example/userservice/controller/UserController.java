package com.example.userservice.controller;

import com.example.userservice.dto.request.ChangePasswordRequest;
import com.example.userservice.dto.request.UserRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity
                .ok()
                .body(userService.getAllUsers());
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser() {
        log.info("Get User controller");
        return ResponseEntity
                .ok()
                .body(userService.getUser());
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity
                .ok()
                .body(userService.updateUser(userRequest));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/changePassword")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);
    }

}
