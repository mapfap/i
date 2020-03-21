package com.code.i.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
class ApiError {

    private HttpStatus status;
    private Instant timestamp;
    private String message;

    ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        timestamp = Instant.now();
    }
}