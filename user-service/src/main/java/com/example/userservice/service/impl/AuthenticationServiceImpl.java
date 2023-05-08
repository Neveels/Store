package com.example.userservice.service.impl;

import com.example.userservice.aws.enums.Path;
import com.example.userservice.config.JwtService;
import com.example.userservice.dto.mapper.UserMapper;
import com.example.userservice.dto.request.AuthenticationRequest;
import com.example.userservice.dto.request.RegisterRequest;
import com.example.userservice.dto.response.AuthenticationResponse;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.User;
import com.example.userservice.entity.enums.Role;
import com.example.userservice.exception.type.user.UserAlreadyExistException;
import com.example.userservice.exception.type.user.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthenticationServiceImpl(UserRepository repository,
                                     PasswordEncoder passwordEncoder,
                                     JwtService jwtService,
                                     AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.findByEmail(request.getEmail()).isEmpty()) {
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .avatar(Path.DEFAULT_PATH.getUrl())
                    .build();
            var savedUser = repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .userResponse(userMapper.toResponseDto(savedUser))
                    .build();
        } else {
            throw new UserAlreadyExistException(String.format("User with email %s already exist",
                    request.getEmail())
            );
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found",
                        request.getEmail()))
                );
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userResponse(userMapper.toResponseDto(user))
                .build();
    }

}