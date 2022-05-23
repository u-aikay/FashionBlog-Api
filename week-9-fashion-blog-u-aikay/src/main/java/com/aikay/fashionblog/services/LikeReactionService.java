package com.aikay.fashionblog.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LikeReactionService {
    ResponseEntity<Object> likePost(long postId, long userId);
    ResponseEntity<Object> likeComment(long postId, long comment, long userId);
}
