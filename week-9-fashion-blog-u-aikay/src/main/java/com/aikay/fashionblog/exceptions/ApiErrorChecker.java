package com.aikay.fashionblog.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiErrorChecker {
    private Date timeStamp;
    private String message;
//    private HttpStatus status ;

}
