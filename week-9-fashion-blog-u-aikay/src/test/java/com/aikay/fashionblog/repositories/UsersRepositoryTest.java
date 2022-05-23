package com.aikay.fashionblog.repositories;

import com.aikay.fashionblog.models.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepositoryTest;

    @Test
    void itShouldFindUsersByEmail() {
        //given
        Users testUsers = Users.builder()
                .userId(1L)
                .usersFirstName("aikay")
                .usersLastName("uche")
                .usersEmail("aikay@mail.com")
                .usersPassword("12345")
                .usersConfirmPassword("12345")
                .usersRole("BLOGGER")
                .build();

        usersRepositoryTest.save(testUsers);

        //when
        Optional<Users> expected = usersRepositoryTest.findByUsersEmail("aikay@mail.com");

        //then
        assertTrue(expected.isPresent());
    }
}