package com.imjcm.back_attend_system.exception;

import java.io.Serial;

public class InternalServerErrorException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public InternalServerErrorException(String message) {
        super(message);
    }
}
