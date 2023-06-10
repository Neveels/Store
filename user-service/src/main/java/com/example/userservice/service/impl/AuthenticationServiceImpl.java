package com.example.userservice.service.impl;

import com.example.userservice.aws.enums.PHOTO_PATH;
import com.example.userservice.config.JwtService;
import com.example.userservice.dto.mapper.UserMapper;
import com.example.userservice.dto.request.AuthenticationRequest;
import com.example.userservice.dto.request.RegisterRequest;
import com.example.userservice.dto.response.AuthenticationResponse;
import com.example.userservice.entity.User;
import com.example.userservice.entity.enums.USER_ROLE;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.userservice.constants.Constants.USER_WITH_EMAIL_ALREADY_EXIST;
import static com.example.userservice.constants.Constants.USER_WITH_EMAIL_NOT_FOUND;

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
                    .role(USER_ROLE.USER)
                    .avatar(PHOTO_PATH.DEFAULT_PATH.getUrl())
                    .build();
            var savedUser = repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .userResponse(userMapper.toResponseDto(savedUser))
                    .build();
        } else {
            throw new BusinessException(String.format(USER_WITH_EMAIL_ALREADY_EXIST,
                    request.getEmail()), HttpStatus.CONFLICT);
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
                .orElseThrow(() -> new BusinessException(String.format(USER_WITH_EMAIL_NOT_FOUND,
                        request.getEmail()), HttpStatus.NOT_FOUND)
                );
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userResponse(userMapper.toResponseDto(user))
                .build();
    }

}
