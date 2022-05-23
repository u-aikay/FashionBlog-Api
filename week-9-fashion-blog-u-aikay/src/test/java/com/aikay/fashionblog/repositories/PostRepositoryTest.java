package com.aikay.fashionblog.repositories;

import com.aikay.fashionblog.models.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepositoryTest;

    Post testPost = Post.builder()
            .postId(1L)
            .postTitle("POST TEST")
            .postCategory("TEST")
            .postBody("FIRST POST TEST")
            .build();


    @Test
    void itShouldFindPostByThePostTitle() {
        //given
        postRepositoryTest.save(testPost);

        //when
        Optional<Post> expected = postRepositoryTest.findPostByPostTitle("POST TEST");

        //then
        Assertions.assertThat(expected.isPresent());
    }

    @Test
    void findPostByPostId() {
        //given
        postRepositoryTest.save(testPost);

        //when
        Post expected = (Post) postRepositoryTest.findPostByPostId(1L);

        //then
        assertEquals(expected.getPostId(), testPost.getPostId());
    }
}