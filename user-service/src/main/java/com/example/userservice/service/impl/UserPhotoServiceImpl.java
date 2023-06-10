package com.example.userservice.service.impl;

import com.example.userservice.aws.enums.PHOTO_PATH;
import com.example.userservice.aws.service.PhotoStorageService;
import com.example.userservice.entity.User;
import com.example.userservice.exception.type.BusinessException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserPhotoService;
import com.example.userservice.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

import static com.example.userservice.constants.Constants.DELETE_PHOTO_MESSAGE;

@Service
public class UserPhotoServiceImpl implements UserPhotoService {
    private final PhotoStorageService photoStorageService;
    private final UserRepository userRepository;
    private final UserUtils userUtils;

    public UserPhotoServiceImpl(PhotoStorageService photoStorageService, UserRepository userRepository, UserUtils userUtils) {
        this.photoStorageService = photoStorageService;
        this.userRepository = userRepository;
        this.userUtils = userUtils;
    }

    @Override
    public File getUserPhoto(String imagePath) {
        return photoStorageService.getFile(PHOTO_PATH.USER, imagePath);
    }

    @Override
    public String addUserPhoto(MultipartFile multipartFile) {
        User user = userUtils.getUser();
        UUID uuid = UUID.randomUUID();
        String path = "%s.jpg".formatted(uuid);
        user.setAvatar(path);
        userRepository.save(user);
        return photoStorageService.uploadFile(PHOTO_PATH.USER, path, multipartFile);
    }

    @Override
    public String deleteUserPhoto() {
        User user = userUtils.getUser();
        String photoPath = user.getAvatar();
        if (!photoPath.equals(PHOTO_PATH.DEFAULT_PATH.getUrl())) {
            String fileName = photoStorageService.deleteFile(PHOTO_PATH.USER, photoPath);
            user.setAvatar(PHOTO_PATH.DEFAULT_PATH.getUrl());
            userRepository.save(user);
            return fileName;
        } else {
            throw new BusinessException(DELETE_PHOTO_MESSAGE, HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public String addDefaultPhoto(MultipartFile file) {
        return photoStorageService.uploadFile(PHOTO_PATH.USER, file.getOriginalFilename(), file);
    }

}
