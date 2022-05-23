package com.aikay.fashionblog.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandlers extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<Object> handleAnyApiExceptions(Exception ex){
            String errorMessage = ex.getLocalizedMessage();
            if(errorMessage == null){
                errorMessage = ex.toString();
            }
            ApiErrorChecker apexHand = new ApiErrorChecker(new Date(), errorMessage);
            return new ResponseEntity<>(
                    apexHand, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED
            );
        }
}