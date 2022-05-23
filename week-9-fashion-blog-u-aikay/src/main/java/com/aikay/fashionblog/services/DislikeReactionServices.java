package com.aikay.fashionblog.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface DislikeReactionServices {
    ResponseEntity<Object> dislikePost(long postId, long userId);
    ResponseEntity<Object> dislikeComment(long postId, long commentId, long userId);

}
