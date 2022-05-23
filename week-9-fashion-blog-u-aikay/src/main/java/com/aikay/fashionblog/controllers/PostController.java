package com.aikay.fashionblog.controllers;

import com.aikay.fashionblog.dtos.PostDto;
import com.aikay.fashionblog.services.DislikeReactionServices;
import com.aikay.fashionblog.services.LikeReactionService;
import com.aikay.fashionblog.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/post")
@RequestMapping("/api")
public class PostController {

    private final PostServices postServices;
    private final LikeReactionService likeReactionService;
    private final DislikeReactionServices dislikeReactionServices;

    @Autowired
    public PostController(PostServices postServices,
                          LikeReactionService likeReactionService,
                          DislikeReactionServices dislikeReactionServices) {
        this.postServices = postServices;
        this.likeReactionService = likeReactionService;
        this.dislikeReactionServices = dislikeReactionServices;
    }


    @PostMapping("/user/{userId}/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable("userId") Long userId){
        return postServices.createPost(postDto, userId);
    }

    @PutMapping(path = "/{postId}/user/{userId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("postId") Long postId,
                                              @PathVariable("userId") Long userId,
                                              @RequestBody PostDto postDto){
        return postServices.updatePost(postDto, postId, userId);
    }

    @GetMapping(path = "/{postTitle}")
    public ResponseEntity<PostDto> displayOnePost(@PathVariable("postTitle") String postTitle){
        return  postServices.viewAPost(postTitle);
    }

    @GetMapping(path = "/display")
    public ResponseEntity<List<PostDto>> displayAllPost(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
            ){
        return postServices.viewAllPost(pageNo, pageSize);
    }

    @DeleteMapping(path = "/{postId}/user/{userId}")
    public ResponseEntity<Object> removePost(@PathVariable("postId") long postId,
                                             @PathVariable("userId") long userId){
        return postServices.deletePost(postId, userId);
    }

    @PostMapping(path = "/{postId}/users/{userId}")
    public ResponseEntity<Object> thumbsUp(@PathVariable("postId") long postId,
                                           @PathVariable("userId") long userId){
        return likeReactionService.likePost(postId, userId);
    }

    @PostMapping(path = "/{postId}/user/{userId}")
    public ResponseEntity<Object> thumbsDown(@PathVariable("postId") long postId,
                                             @PathVariable("userId") long userId){
        return dislikeReactionServices.dislikePost(postId, userId);
    }
}
//todo "git push origin aikayBranch"