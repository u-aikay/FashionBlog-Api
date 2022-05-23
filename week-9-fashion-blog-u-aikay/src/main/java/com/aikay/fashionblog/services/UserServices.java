package com.aikay.fashionblog.services;

import com.aikay.fashionblog.dtos.LoginDto;
import com.aikay.fashionblog.dtos.UserDto;
import com.aikay.fashionblog.models.Users;
import org.springframework.http.ResponseEntity;

public interface UserServices {
    ResponseEntity<Users>  createBlogger(UserDto userDto);
    ResponseEntity<Users> createReader(UserDto userDto);
    ResponseEntity<Users> login(LoginDto loginDto);
}
