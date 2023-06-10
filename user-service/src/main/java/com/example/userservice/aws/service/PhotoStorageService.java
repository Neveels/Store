package com.example.userservice.aws.service;

import com.example.userservice.aws.enums.PHOTO_PATH;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface PhotoStorageService {
    String uploadFile(PHOTO_PATH path, String photoPath, MultipartFile file);
    String uploadFile( String photoPath, MultipartFile file);
    File getFile(PHOTO_PATH path, String fileName);
    File getFile(String path, String filename);
    String deleteFile(PHOTO_PATH path, String fileName);
    String deleteFile(String fileName);
}

