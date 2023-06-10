package com.example.userservice.aws.service.impl;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.userservice.aws.enums.PHOTO_PATH;
import com.example.userservice.aws.service.PhotoStorageService;
import com.example.userservice.exception.type.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

import static com.example.userservice.constants.Constants.AMAZON_MESSAGE;
import static com.example.userservice.constants.Constants.FILE_DOES_NOT_EXIST;

@Slf4j
@Service
public class PhotoStorageServiceImpl implements PhotoStorageService {
    private final AmazonS3 s3client;

    @Value("${amazon.bucket}")
    private String bucket;

    public PhotoStorageServiceImpl(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    @Override
    public String uploadFile(PHOTO_PATH path, String photoPath, MultipartFile file) {
        try {
            File tmp = File.createTempFile("test", file.getOriginalFilename());
            file.transferTo(tmp);
            s3client.putObject(bucket, path.getUrl() + photoPath, tmp);
            return photoPath;
        } catch (AmazonServiceException e) {
            throw new BusinessException(AMAZON_MESSAGE + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            throw new BusinessException(FILE_DOES_NOT_EXIST, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String uploadFile(String photoPath, MultipartFile file) {
        try {
            File tmp = File.createTempFile("test", file.getOriginalFilename());
            file.transferTo(tmp);
            s3client.putObject(bucket, photoPath, tmp);
            return photoPath;
        } catch (AmazonServiceException e) {
            throw new BusinessException(AMAZON_MESSAGE + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            throw new BusinessException(FILE_DOES_NOT_EXIST, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public File getFile(PHOTO_PATH path, String fileName) {
        try {
            if (!s3client.doesObjectExist(bucket, path.getUrl() + fileName)) {
                throw new BusinessException(FILE_DOES_NOT_EXIST, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                File tmp = File.createTempFile("storeAppTmpRead", fileName);
                FileOutputStream fos = new FileOutputStream(tmp);
                S3Object o = s3client.getObject(bucket, path.getUrl() + fileName);
                S3ObjectInputStream s3is = o.getObjectContent();
                byte[] bytes = IOUtils.toByteArray(s3is);
                fos.write(bytes);
                s3is.close();
                fos.flush();
                fos.close();
                return tmp;
            }
        } catch (AmazonServiceException e) {
            throw new BusinessException(AMAZON_MESSAGE + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            throw new BusinessException(FILE_DOES_NOT_EXIST, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public File getFile(String path, String filename) {
        try {
            if (!s3client.doesObjectExist(bucket, path + filename)) {
                throw new BusinessException(FILE_DOES_NOT_EXIST, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                File tmp = File.createTempFile("storeAppTmpRead", filename);
                FileOutputStream fos = new FileOutputStream(tmp);
                S3Object o = s3client.getObject(bucket, path + filename);
                S3ObjectInputStream s3is = o.getObjectContent();
                byte[] bytes = IOUtils.toByteArray(s3is);
                fos.write(bytes);
                s3is.close();
                fos.flush();
                fos.close();
                return tmp;
            }
        } catch (AmazonServiceException e) {
            throw new BusinessException(AMAZON_MESSAGE + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            throw new BusinessException(FILE_DOES_NOT_EXIST, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public String deleteFile(PHOTO_PATH path, String fileName) {
        log.info("delete file with path {} and filename {}", path, fileName);
        try {
            s3client.deleteObject(bucket, path.getUrl() + fileName);
            return fileName;
        } catch (AmazonServiceException e) {
            throw new BusinessException(AMAZON_MESSAGE + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            throw new BusinessException(FILE_DOES_NOT_EXIST, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public String deleteFile(String fileName) {
        log.info("delete file with filename {}", fileName);
        try {
            s3client.deleteObject(bucket, fileName);
            return fileName;
        } catch (AmazonServiceException e) {
            throw new BusinessException(AMAZON_MESSAGE + e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            throw new BusinessException(FILE_DOES_NOT_EXIST, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
