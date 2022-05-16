package com.example.kointalkchatroom.exception.status;

import com.example.kointalkchatroom.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends GlobalException {

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException(String reason) {
        super(HttpStatus.UNAUTHORIZED, reason);
    }

    public UnauthorizedException(String reason, Throwable cause) {
        super(HttpStatus.UNAUTHORIZED, reason, cause);
    }
}
