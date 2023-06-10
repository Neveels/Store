package com.example.userservice.exception.handler;

import com.example.userservice.exception.model.Exception;
import com.example.userservice.exception.type.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException e) {
        HttpStatus status = e.getHttpStatus();
        Exception ex = Exception.builder()
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .httpStatus(e.getHttpStatus())
                .build();
        return new ResponseEntity<>(ex, status);
    }

}
