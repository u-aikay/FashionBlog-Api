package com.aikay.fashionblog.services;

import com.aikay.fashionblog.dtos.PostDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface PostServices {
    ResponseEntity<PostDto> createPost(PostDto postDto, long userId);
    ResponseEntity<PostDto> updatePost(PostDto postDto,
                                       long postId,
                                       long userId);
    ResponseEntity<PostDto> viewAPost(String postTitle);
    ResponseEntity<List<PostDto>> viewAllPost(int pageNo, int pageSize);
    ResponseEntity<Object> deletePost(long postId, long userId);
}