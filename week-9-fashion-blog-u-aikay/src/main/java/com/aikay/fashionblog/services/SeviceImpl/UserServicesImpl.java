package com.aikay.fashionblog.services.SeviceImpl;

import com.aikay.fashionblog.dtos.LoginDto;
import com.aikay.fashionblog.dtos.UserDto;
import com.aikay.fashionblog.models.Users;
import com.aikay.fashionblog.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServicesImpl implements com.aikay.fashionblog.services.UserServices {

    private UsersRepository usersRepository;

    @Autowired
    public UserServicesImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public ResponseEntity<Users> createBlogger(UserDto userDto) {
        Users user = new Users();
        user.setUsersFirstName(userDto.getUsersFirstName());
        user.setUsersLastName(userDto.getUsersLastName());
        user.setUsersEmail(userDto.getUsersEmail());
        user.setUsersPassword(userDto.getUsersPassword());
        user.setUsersConfirmPassword(userDto.getUsersConfirmPassword());
        user.setUsersRole("BLOGGER");

        if(!Objects.equals(userDto.getUsersPassword(), user.getUsersConfirmPassword())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            Users savedUser = usersRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Users> createReader(UserDto userDto) {
        Users user = new Users();
        user.setUsersFirstName(userDto.getUsersFirstName());
        user.setUsersLastName(userDto.getUsersLastName());
        user.setUsersEmail(userDto.getUsersEmail());
        user.setUsersPassword(userDto.getUsersPassword());
        user.setUsersConfirmPassword(userDto.getUsersConfirmPassword());
        user.setUsersRole("READER");

        if(!Objects.equals(userDto.getUsersPassword(), user.getUsersConfirmPassword())){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            Users savedUser = usersRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }
    }

    @Override
    public ResponseEntity<Users> login(LoginDto loginDto) {
        Optional<Users> user = usersRepository.findByUsersEmail(loginDto.getUsersEmail());

        if(user.isPresent()){
            Users foundUser = user.get();
            if(Objects.equals(foundUser.getUsersPassword(), loginDto.getUsersPassword())){
                return new ResponseEntity<>(foundUser, HttpStatus.FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
