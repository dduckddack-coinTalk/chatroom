package com.example.kointalkchatroom.exception.status;

import com.example.kointalkchatroom.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends GlobalException {

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

    public BadRequestException(String reason, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, reason, cause);
    }
}
