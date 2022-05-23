package com.aikay.fashionblog.services.SeviceImpl;

import com.aikay.fashionblog.models.Comment;
import com.aikay.fashionblog.models.DislikeReactions;
import com.aikay.fashionblog.models.Post;
import com.aikay.fashionblog.models.Users;
import com.aikay.fashionblog.repositories.CommentRepository;
import com.aikay.fashionblog.repositories.DislikeReactionRepository;
import com.aikay.fashionblog.repositories.PostRepository;
import com.aikay.fashionblog.repositories.UsersRepository;
import com.aikay.fashionblog.services.DislikeReactionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DislikeReactionServiceImpl implements DislikeReactionServices {
    private final UsersRepository usersRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final DislikeReactionRepository dislikeReactionRepository;

    public DislikeReactionServiceImpl(UsersRepository usersRepository,
                                      PostRepository postRepository,
                                      CommentRepository commentRepository,
                                      DislikeReactionRepository dislikeReactionRepository) {
        this.usersRepository = usersRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.dislikeReactionRepository = dislikeReactionRepository;
    }

    @Override
    public ResponseEntity<Object> dislikePost(long postId, long userId) {
        Optional<Users> users = usersRepository.findById(userId);
        if(users.isPresent()){
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()){
                Optional<DislikeReactions> dislikeP = dislikeReactionRepository.findById(userId);
                if(dislikeP.isPresent()){
                    return new ResponseEntity<>("You only get to like ones", HttpStatus.LOCKED);
                }else{
                    DislikeReactions dislikeReactions = new DislikeReactions();
                    dislikeReactions.setPostId(post.get().getPostId());
                    dislikeReactions.setUserId(users.get().getUserId());
                    return new ResponseEntity<>("liked", HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> dislikeComment(long postId, long commentId, long userId) {
        Optional<Users> users = usersRepository.findById(userId);
        if(users.isPresent()){
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()) {
                Optional<Comment> comment = commentRepository.findById(commentId);
                if(comment.isPresent()) {
                    Optional<DislikeReactions> dislikeC = dislikeReactionRepository.findById(userId);
                    if (dislikeC.isPresent()) {
                        return new ResponseEntity<>("You only get to like ones", HttpStatus.LOCKED);
                    } else {
                        DislikeReactions dislikeReactions = new DislikeReactions();
                        dislikeReactions.setUserId(users.get().getUserId());
                        dislikeReactions.setCommentId(comment.get().getCommentId());
                        dislikeReactions.setPostId(post.get().getPostId());
                        return new ResponseEntity<>("liked", HttpStatus.OK);
                    }
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
