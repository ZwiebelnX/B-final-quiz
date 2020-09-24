package com.example.demo.utils;


import com.example.demo.module.exception.BusinessBasicException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessBasicException.class)
    public ResponseEntity<Void> businessBasicExceptionHandler(BusinessBasicException businessBasicException) {
        return new ResponseEntity<>(businessBasicException.getHttpStatus());
    }
}
