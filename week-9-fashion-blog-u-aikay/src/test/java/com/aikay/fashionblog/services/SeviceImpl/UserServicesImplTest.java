package com.aikay.fashionblog.services.SeviceImpl;

import com.aikay.fashionblog.dtos.LoginDto;
import com.aikay.fashionblog.dtos.UserDto;
import com.aikay.fashionblog.models.Users;
import com.aikay.fashionblog.repositories.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class  UserServicesImplTest {

    @InjectMocks
    private UserServicesImpl userServices;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UserDto userDto1;
    @Mock
    private Users newBlogger;
    @Mock
    private Users newReader;
    @Mock
    private LoginDto loginDto;

    @BeforeEach
    void setUp(){
        String usersFirstName = "Aikay";
        String usersLastName = "Uchey";
        String usersEmail = "ai@mail.com";
        String usersPassword = "12345";
        String usersConfirmPassword = "12345";
        String usersRole = "BLOGGER";

        userDto1 = UserDto.builder()
                .usersFirstName("firstName")
                .usersLastName("lastName")
                .usersEmail("email")
                .usersPassword("123456")
                .usersConfirmPassword("123456").build();

        loginDto = LoginDto.builder()
                .usersEmail(usersEmail)
                .usersPassword(usersPassword)
                .build();

        newBlogger = Users.builder()
                .userId(2L)
                .usersFirstName(usersFirstName)
                .usersLastName(usersLastName)
                .usersEmail(usersEmail)
                .usersRole(usersRole)
                .usersPassword(usersPassword)
                .usersConfirmPassword(usersConfirmPassword)
                .build();

        newReader = Users.builder()
                .userId(3L)
                .usersFirstName(usersFirstName)
                .usersLastName(usersLastName)
                .usersEmail(usersEmail)
                .usersRole("READER")
                .usersPassword(usersPassword)
                .usersConfirmPassword(usersConfirmPassword)
                .build();

//        Mockito.when(usersRepository.save(any())).thenReturn(newBlogger);
    }

    @Test
    @DisplayName("TEST: create blogger")
    void shouldReturnUserDtoAfterCreatingNewBlogger() {
        Mockito.when(usersRepository.save(any())).thenReturn(newBlogger);
        ResponseEntity<Users> bloggerResponseEntity = userServices.createBlogger(userDto1);
        Assertions.assertThat(bloggerResponseEntity.getBody()).isEqualTo(newBlogger);
    }

    @Test
    @DisplayName(("Test: create reader"))
    void shouldAcknowledgeNewReaderAccountCreated() {
        Mockito.when(usersRepository.save(any())).thenReturn(newReader);
        ResponseEntity<Users> readerResponseEntity = userServices.createReader(userDto1);
        Assertions.assertThat(readerResponseEntity.getBody()).isEqualTo(newReader);
    }

    @Test
    @DisplayName(("Test: login user"))
    void shouldFindAUserThroughUserRepositoryByEmail(){

        Mockito.when(usersRepository.save(any())).thenReturn(newReader);
        ResponseEntity<Users> responseEntity = userServices.login(loginDto);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
    }
}