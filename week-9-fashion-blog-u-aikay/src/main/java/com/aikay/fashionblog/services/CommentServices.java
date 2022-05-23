package com.aikay.fashionblog.services;

import com.aikay.fashionblog.dtos.CommentDto;
import com.aikay.fashionblog.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface CommentServices {
    ResponseEntity<Object> createComment(CommentDto commentDto, long postId, long userId);

    ResponseEntity<Object> viewAComment(long postId, long commentId);
    ResponseEntity<List<CommentDto>> viewAllComment(long postId);
    ResponseEntity<Object> deleteComment(long postId, long commentId, long userId);
}
