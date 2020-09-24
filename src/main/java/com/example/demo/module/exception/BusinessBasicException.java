package com.example.demo.module.exception;

import org.springframework.http.HttpStatus;

public class BusinessBasicException extends Exception {

    private final HttpStatus httpStatus;

    public BusinessBasicException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
