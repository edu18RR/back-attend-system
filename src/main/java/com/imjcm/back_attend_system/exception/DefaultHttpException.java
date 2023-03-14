package com.imjcm.back_attend_system.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class DefaultHttpException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;

    public DefaultHttpException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
