package com.code.i.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
class ApiError {

    private HttpStatus status;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Instant timestamp;
    private String message;

    ApiError(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        timestamp = Instant.now();
    }
}