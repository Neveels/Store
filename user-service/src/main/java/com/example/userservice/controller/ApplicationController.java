package com.example.userservice.controller;

import com.example.userservice.dto.request.ApplicationRequest;
import com.example.userservice.dto.response.ApplicationResponse;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("applications")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> addApplication(@RequestBody ApplicationRequest applicationRequest) {
        return ResponseEntity
                .ok()
                .body(applicationService.addApplication(applicationRequest));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplications() {
        return ResponseEntity
                .ok()
                .body(applicationService.getAllApplications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateToUser(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(applicationService.updateToUser(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteApplicationById(@PathVariable Integer id) {
        applicationService.deleteById(id);
    }
}
