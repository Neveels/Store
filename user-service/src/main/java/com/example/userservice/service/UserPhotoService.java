package com.example.userservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UserPhotoService {
    File getUserPhoto(String imagePath);
    String addUserPhoto(MultipartFile multipartFile);
    String deleteUserPhoto();
    String addDefaultPhoto(MultipartFile file);

}
