package com.imjcm.back_attend_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(NotFoundException e, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.NOT_FOUND.value()).timestamp(new Date()).message(e.getMessage()).description(request.getDescription(false)).build();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorMessage> requestExceptionHandler(BadRequestException e, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.BAD_REQUEST.value()).timestamp(new Date()).message(e.getMessage()).description(request.getDescription(false)).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InternalServerErrorException.class)
    public ResponseEntity<ErrorMessage> internalExceptionHandler(InternalServerErrorException e, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).timestamp(new Date()).message(e.getMessage()).description(request.getDescription(false)).build();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DefaultHttpException.class)
    public ResponseEntity<ErrorMessage> defaultExceptionHandler(DefaultHttpException e, WebRequest request) {
        ErrorMessage message = ErrorMessage.builder().statusCode(e.getHttpStatus().value()).timestamp(new Date()).message(e.getMessage()).description(request.getDescription(false)).build();
        return new ResponseEntity<>(message, e.getHttpStatus());
    }
}
