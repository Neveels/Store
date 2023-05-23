package com.example.userservice.service.impl;

import com.example.userservice.aws.enums.Path;
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
        return photoStorageService.getFile(Path.USER, imagePath);
    }

    @Override
    public String addUserPhoto(MultipartFile multipartFile) {
        User user = userUtils.getUser();
        UUID uuid = UUID.randomUUID();
        String path = uuid + ".jpg";
        user.setAvatar(path);
        userRepository.save(user);
        return photoStorageService.uploadFile(Path.USER, path, multipartFile);
    }

    @Override
    public void updateUserPhoto(String path, MultipartFile multipartFile) {

    }

    @Override
    public String deleteUserPhoto() {
        User user = userUtils.getUser();
        String photoPath = user.getAvatar();
        if (!photoPath.equals(Path.DEFAULT_PATH.getUrl())) {
            String fileName = photoStorageService.deleteFile(Path.USER, photoPath);
            user.setAvatar(Path.DEFAULT_PATH.getUrl());
            userRepository.save(user);
            return fileName;
        } else {
            throw new BusinessException("You cannot delete default user photo", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public String addDefaultPhoto(MultipartFile file) {
        return photoStorageService.uploadFile(Path.USER, file.getOriginalFilename(), file);
    }

}
