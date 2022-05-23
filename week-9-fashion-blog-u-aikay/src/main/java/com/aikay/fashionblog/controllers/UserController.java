package com.aikay.fashionblog.controllers;

import com.aikay.fashionblog.dtos.LoginDto;
import com.aikay.fashionblog.dtos.UserDto;
import com.aikay.fashionblog.models.Users;
import com.aikay.fashionblog.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@RestController
//@RequestMapping("/user")
@RequestMapping("/api")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping(path = "/bloggerSignup")
    public ResponseEntity<UserDto> bloggerSignUp(@RequestBody UserDto userDto){
        userServices.createBlogger(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(path = "/readerSignup")
    public ResponseEntity<UserDto> readerSignUp(@RequestBody UserDto userDto){
        userServices.createReader(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public  ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto, HttpSession session){
        ResponseEntity<Users> user = userServices.login(loginDto);
        session.setAttribute("user", user.getBody());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}