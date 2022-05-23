package com.aikay.fashionblog.services.SeviceImpl;

import com.aikay.fashionblog.dtos.PostDto;
import com.aikay.fashionblog.dtos.UserDto;
import com.aikay.fashionblog.models.Post;
import com.aikay.fashionblog.models.Users;
import com.aikay.fashionblog.repositories.PostRepository;
import com.aikay.fashionblog.repositories.UsersRepository;
import com.aikay.fashionblog.services.PostServices;
import com.aikay.fashionblog.services.UserServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {


    @Mock
    private PostRepository postRepository;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserServices userServices;

    @InjectMocks
    private PostServiceImpl postService;


    private PostDto postDto;
    private Post post;
    private Users users;
    private UserDto userDto;

//    @MockBean
//    private Users users1;
//    @MockBean
//    private UserDto userDto;

    @BeforeEach
    void setUp() {
        List<Post> postList = new ArrayList<>();
        String postTitle = "POST1";
        String postCategory = "FASHION";
        String postBody = "1st post unit test";

        postDto = PostDto.builder()
                .postTitle(postTitle)
                .postCategory(postCategory)
                .postBody(postBody)
                .build();

        post = Post.builder()
                .postId(1L)
                .postTitle(postTitle)
                .postCategory(postCategory)
                .postBody(postBody)
                .blogger(users)
                .build();

        users = Users.builder()
                .userId(1L)
                .usersFirstName("firstName")
                .usersLastName("usersLastName")
                .usersEmail("usersEmail")
                .usersRole("BLOGGER")
                .usersPassword("usersPassword")
                .usersConfirmPassword("usersPassword")
                .build();

            userDto = UserDto.builder()
//                    .userId(1L)
                    .usersFirstName("firstName")
                    .usersLastName("usersLastName")
                    .usersEmail("usersEmail")
//                    .usersRole("BLOGGER")
                    .usersPassword("usersPassword")
                    .usersConfirmPassword("usersPassword")
                    .build();


//        users1 = Users.builder()
//                .userId(2L)
//                .usersFirstName("firstName")
//                .usersLastName("usersLastName")
//                .usersEmail("usersEmail")
//                .usersRole("READER")
//                .usersPassword("usersPassword")
//                .usersConfirmPassword("usersPassword")
//                .build();



    }

    @Test
    @DisplayName("Test: create post")
    void shouldReturnHttpStatus_CreatedAfterCreatingAPost() {
//        Mockito.when(usersRepository.save(users)).thenReturn(users);
        Mockito.when(postRepository.save(any())).thenReturn(post);
//        when(userServices.createBlogger(userDto)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(users));
//        given(usersRepository.findById(any())).willReturn(Optional.of(users));
        System.out.println(users);
//        PostDto postDto1 = modelMapper.map(post, PostDto.class);
        ResponseEntity<PostDto> create = postService.createPost(postDto, 1L);

        assertEquals(create.getStatusCode(), HttpStatus.CREATED);
    }

//    @Test
//    @DisplayName("Test: update user")
//    void shouldReturnUserDtoAfterUpdatingUser(){
//        Users users = usersRepository.save(newBlogger);
//        UserDto userDto = modelMapper.map(newBlogger, UserDto.class);
//        userDto.setUsersFirstName("updated");
//        ResponseEntity<UserDto> responseEntity = userServices.
//    }

    @Test
    void viewAPost() {
    }

    @Test
    void viewAllPost() {
    }

    @Test
    void deletePost() {
    }
}