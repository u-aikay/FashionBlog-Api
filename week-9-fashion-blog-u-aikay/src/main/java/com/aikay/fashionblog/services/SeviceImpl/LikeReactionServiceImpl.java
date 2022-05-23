package com.aikay.fashionblog.services.SeviceImpl;

import com.aikay.fashionblog.models.Comment;
import com.aikay.fashionblog.models.Post;
import com.aikay.fashionblog.models.LikeReactions;
import com.aikay.fashionblog.models.Users;
import com.aikay.fashionblog.repositories.CommentRepository;
import com.aikay.fashionblog.repositories.PostRepository;
import com.aikay.fashionblog.repositories.LikeReactionRepository;
import com.aikay.fashionblog.repositories.UsersRepository;
import com.aikay.fashionblog.services.LikeReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LikeReactionServiceImpl implements LikeReactionService {
    private final LikeReactionRepository likeReactionRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public LikeReactionServiceImpl(LikeReactionRepository likeReactionRepository,
                                   PostRepository postRepository,
                                   CommentRepository commentRepository,
                                   UsersRepository usersRepository) {
        this.likeReactionRepository = likeReactionRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public ResponseEntity<Object> likePost(long postId, long userId) {
        Optional<Users> user = usersRepository.findById(userId);
        if(user.isPresent()){
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()){
                Optional<LikeReactions> likeP = likeReactionRepository.findById(userId);
                if(likeP.isPresent()) {
                    return new ResponseEntity<>("You only get to like ones", HttpStatus.LOCKED);
                }else{
                    likeP.get().setPostId(post.get().getPostId());
                    likeP.get().setUserId(user.get().getUserId());
                    return new ResponseEntity<>("liked", HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> likeComment(long postId, long commentId, long userId) {
        Optional<Users> users = usersRepository.findById(userId);
        if(users.isPresent()){
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()){
                Optional<Comment> comment = commentRepository.findById(commentId);
                if(comment.isPresent()){
                    Optional<LikeReactions> likeC = likeReactionRepository.findById(userId);
                    if(likeC.isPresent()){
                        return new ResponseEntity<>("You only get to like ones", HttpStatus.LOCKED);
                    }else{
                        LikeReactions likeReactions = new LikeReactions();
                        likeReactions.setUserId(users.get().getUserId());
                        likeReactions.setCommentId(comment.get().getCommentId());
                        likeReactions.setPostId(post.get().getPostId());
                        return new ResponseEntity<>("liked", HttpStatus.OK);
                    }
                }
            }

        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
