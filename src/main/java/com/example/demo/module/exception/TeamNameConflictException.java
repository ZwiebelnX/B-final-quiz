package com.example.demo.module.exception;

import org.springframework.http.HttpStatus;

public class TeamNameConflictException extends BusinessBasicException {

    public TeamNameConflictException() {
        super(HttpStatus.CONFLICT, "队名已存在");
    }
}
