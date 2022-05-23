package com.aikay.fashionblog.repositories;

import com.aikay.fashionblog.models.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepositoryTest;

    @Test
    void itShouldFindCommentByCommentTitle() {
        //given
        Comment testComment = Comment.builder()
                .commentId(1L)
                .commentTitle("TEST COMMENT")
                .comment("I'M GETTING BETTER")
                .build();

        commentRepositoryTest.save(testComment);

        //when
        Optional<Comment> expected = commentRepositoryTest.findByCommentTitle("TEST COMMENT");

        //then
        assertTrue(expected.isPresent());
    }
}