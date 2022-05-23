package com.aikay.fashionblog.controllers;

import com.aikay.fashionblog.dtos.CommentDto;
import com.aikay.fashionblog.models.Comment;
import com.aikay.fashionblog.services.CommentServices;
import com.aikay.fashionblog.services.DislikeReactionServices;
import com.aikay.fashionblog.services.LikeReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//@RequestMapping("/post")
@RequestMapping("/api")
public class CommentController {
    private final CommentServices commentServices;
    private final LikeReactionService likeReactionService;
    private final DislikeReactionServices dislikeReactionServices;

    @Autowired
    public CommentController(CommentServices commentServices,
                             LikeReactionService likeReactionService,
                             DislikeReactionServices dislikeReactionServices) {
        this.commentServices = commentServices;
        this.likeReactionService = likeReactionService;
        this.dislikeReactionServices = dislikeReactionServices;
    }

    @PostMapping(path = "/{postId}/user/{userId}/comment")
    public ResponseEntity<Object> createComment(@RequestBody CommentDto commentDto,
                                                @PathVariable(value = "postId") long postId,
                                                @PathVariable(value = "userId") long userId) {
        return commentServices.createComment(commentDto, postId, userId);
    }

    @GetMapping(path = "/{postId}/comment/{commentId}")
    public Object viewOneComment(@PathVariable(value = "postId") long postId,
                                 @PathVariable(value = "commentId") long commentId) {
        return commentServices.viewAComment(postId, commentId);
    }

    @GetMapping(path = "/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComment(@PathVariable("postId") Long postId) {
        return commentServices.viewAllComment(postId);
    }

    @DeleteMapping(path = "/{postId}/comment/{commentId}/user/{userId}")
    public ResponseEntity<Object> removeComment(@PathVariable("postId") long postId,
                                                @PathVariable("commentId") long commentId,
                                                @PathVariable("userId") long userId) {
        return commentServices.deleteComment(postId, commentId, userId);
    }

    @PostMapping(path = "/{postId}/user/{userId}/comment/{commentId}")
    public ResponseEntity<Object> thumbsUp(@PathVariable("postId") long postId,
                                           @PathVariable("commentId") long commentId,
                                           @PathVariable("userId") long userId) {
        return likeReactionService.likeComment(commentId, userId, postId);
    }

    @PostMapping(path = "/{postId}/comment/{commentId}/user/{userId}")
    public ResponseEntity<Object> thumbsDown(@PathVariable("postId") long postId,
                                             @PathVariable("commentId") long commentId,
                                             @PathVariable("userId") long userId) {
        return dislikeReactionServices.dislikeComment(commentId, postId, userId);
    }
}