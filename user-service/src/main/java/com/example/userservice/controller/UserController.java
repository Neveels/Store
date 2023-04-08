package com.example.userservice.controller;

import com.example.userservice.dto.request.ChangePasswordRequest;
import com.example.userservice.dto.request.UserRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 2/7/2023 12:49 PM
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //TODO: edit this method
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser() {
        log.info("Get User controller");
        return ResponseEntity.ok(userService.getUser());
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok().body(userService.changePassword(changePasswordRequest));
    }


}
