package com.aikay.fashionblog.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.InvalidClassException;
import java.util.Date;

@ControllerAdvice
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = InvalidClassException.class)
    public ResponseEntity<Object> handleBadRequestExceptions(InvalidClassException ex){
        String errorMessage = ex.getLocalizedMessage();
        if(errorMessage == null){
            errorMessage = ex.toString();
        }
        ApiErrorChecker apexHand = new ApiErrorChecker(new Date(), errorMessage);
        return new ResponseEntity<>(
                apexHand, new HttpHeaders(), HttpStatus.BAD_REQUEST
        );
    }
}