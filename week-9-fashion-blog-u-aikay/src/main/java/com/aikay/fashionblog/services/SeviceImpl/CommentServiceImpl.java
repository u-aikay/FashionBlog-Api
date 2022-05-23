package com.aikay.fashionblog.services.SeviceImpl;

import com.aikay.fashionblog.dtos.CommentDto;
import com.aikay.fashionblog.models.Comment;
import com.aikay.fashionblog.models.Post;
import com.aikay.fashionblog.models.Users;
import com.aikay.fashionblog.repositories.CommentRepository;
import com.aikay.fashionblog.repositories.PostRepository;
import com.aikay.fashionblog.repositories.UsersRepository;
import com.aikay.fashionblog.services.CommentServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentServices {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              UsersRepository usersRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public ResponseEntity<Object> createComment(CommentDto commentDto, long postId, long userId) {
        Optional<Users> user = usersRepository.findById(userId);

        if(user.isPresent()){
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()){
                Comment comment = new Comment();
                comment.setPost(post.get());
                comment.setCommentTitle(commentDto.getCommentTitle());
                comment.setComment(commentDto.getComment());
                comment.setReader(user.get());
                commentRepository.save(comment);
                return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("Post is not available", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Please login or signUp to drop a comment", HttpStatus.BAD_REQUEST);
    }

    //todo recursion error when running on postman, fix up!

//    @Override
//    public ResponseEntity<Object> createComment(CommentDto commentDto, long postId, long userId) {
//        return null;
//    }

    @Override
    public ResponseEntity<Object> viewAComment(long postId, long commentId) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Optional<Comment> comment = commentRepository.findById(commentId);
            if(comment.isPresent()){
                if(comment.get().getPost().getPostId().equals(post.get().getPostId())){
                    return new ResponseEntity<>(comment, HttpStatus.FOUND);
                }
            }
            return new ResponseEntity<>("comment do not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("post do not exist", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<CommentDto>> viewAllComment(long postId) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        List<Comment> commentList = commentRepository.findAll();
        for (Comment everyComment : commentList){
            commentDtoList.add(modelMapper.map(everyComment, CommentDto.class));
        }
        return new ResponseEntity<>(commentDtoList, HttpStatus.FOUND);
    }

    //todo deleteComment in reality deletes the entire post, fix up!
    @Override
    public ResponseEntity<Object> deleteComment(long postId, long commentId, long userId) {
        Optional<Users> user = usersRepository.findById(userId);
        if(user.isPresent() && user.get().getUserId() == userId){
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()){
                Optional<Comment> comment = commentRepository.findById(commentId);
                if(comment.isPresent()){
                    commentRepository.delete(comment.get());
                    return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
                }
                return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>("unauthorised function", HttpStatus.BAD_REQUEST);
    }
}
