package com.aikay.fashionblog.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class NullPointerExceptionHandler extends RuntimeException {
//public class NullPointerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, HttpStatus status){
        String errorMessage = ex.getLocalizedMessage();
        if(errorMessage == null){
            errorMessage = ex.toString();
        }
        ApiErrorChecker apexHand = new ApiErrorChecker(new Date(), errorMessage);
        return new ResponseEntity<>(apexHand, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
