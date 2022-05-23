package com.aikay.fashionblog.services.SeviceImpl;

import com.aikay.fashionblog.dtos.CommentDto;
import com.aikay.fashionblog.models.Comment;
import com.aikay.fashionblog.models.Post;
import com.aikay.fashionblog.models.Users;
import com.aikay.fashionblog.repositories.CommentRepository;
import com.aikay.fashionblog.repositories.PostRepository;
import com.aikay.fashionblog.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private UsersRepository usersRepositoryTest;
    private CommentRepository commentRepositoryTest;
    private PostRepository postRepositoryTest;
    private ModelMapper modelMapper;


    private Comment testComment;
    private Users testUser1;
    private Users testUser2;
    private Post testPost;


    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp(){
        testUser1 = Users.builder()
                .userId(1L)
                .usersFirstName("aikay")
                .usersLastName("uche")
                .usersEmail("aikay@mail.com")
                .usersPassword("12345")
                .usersConfirmPassword("12345")
                .usersRole("BLOGGER")
                .build();

        testUser2 = Users.builder()
                .userId(2L)
                .usersFirstName("micheal")
                .usersLastName("dennis")
                .usersEmail("dan@mail.com")
                .usersPassword("12345")
                .usersConfirmPassword("12345")
                .usersRole("READER")
                .build();

        testPost = Post.builder()
                .postId(1L)
                .postTitle("POST TEST")
                .postCategory("TEST")
                .postBody("FIRST POST TEST")
                .build();

        testComment = Comment.builder()
                .commentId(1L)
                .commentTitle("TEST COMMENT")
                .comment("I'M GETTING BETTER")
                .build();
    }


    @Test
    void itShouldCreateAComment() {

        //given
//        commentService.createComment(testComment, 1L, 2L);

        //when
        //then
//        ArgumentCaptor<Comment> commentArgumentCaptor = ArgumentCaptor.forClass(Comment.class);

//        verify(commentRepositoryTest).save(commentArgumentCaptor.capture());
    }

    @Test
    void viewAComment() {
    }

    @Test
    void viewAllComment() {
    }

    @Test
    void deleteComment() {
    }
}