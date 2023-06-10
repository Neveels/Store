package com.example.userservice.service.impl;

import com.example.userservice.dto.mapper.UserMapper;
import com.example.userservice.dto.request.ChangePasswordRequest;
import com.example.userservice.dto.request.UserRequest;
import com.example.userservice.dto.response.UserResponse;
import com.example.userservice.entity.User;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.userservice.constants.Constants.INCORRECT_PASSWORD_EXCEPTION;
import static com.example.userservice.constants.Constants.SAME_PASSWORD_EXCEPTION;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserUtils userUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, UserUtils userUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userUtils = userUtils;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Override
    public UserResponse getUser() {
        return userMapper.toResponseDto(userUtils.getUser());
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        User userById = userUtils.getUser();
        userById.setFirstName(userRequest.getFirstName());
        userById.setLastName(userRequest.getLastName());
        userById.setPhoneNumber(userRequest.getPhoneNumber());
        return userMapper.toResponseDto(userRepository.save(userById));
    }


    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = userUtils.getUser();
        String oldPassword = user.getPassword();

        if (passwordEncoder.matches(changePasswordRequest.getNewPassword(), oldPassword) ||
                changePasswordRequest.getNewPassword().equals(changePasswordRequest.getOldPassword())
        ) {
            throw new BusinessException(SAME_PASSWORD_EXCEPTION, HttpStatus.CONFLICT);
        }
        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), oldPassword)) {
            throw new BusinessException(INCORRECT_PASSWORD_EXCEPTION, HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }

}
