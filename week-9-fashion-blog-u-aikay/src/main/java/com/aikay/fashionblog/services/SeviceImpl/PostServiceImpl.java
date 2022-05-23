package com.aikay.fashionblog.services.SeviceImpl;

import com.aikay.fashionblog.dtos.PostDto;
import com.aikay.fashionblog.models.Post;
import com.aikay.fashionblog.models.Users;
import com.aikay.fashionblog.repositories.PostRepository;
import com.aikay.fashionblog.repositories.UsersRepository;
import com.aikay.fashionblog.services.PostServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;
import java.util.*;


@Service
public class PostServiceImpl implements PostServices {

    private final PostRepository postRepository;
    private final UsersRepository usersRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UsersRepository usersRepository) {
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public ResponseEntity<PostDto> createPost(PostDto postDto, long userId) {
//        Optional<Users> users = usersRepository.findById(userId);
//        if(users.isPresent() && !Objects.equals(users.get().getUsersRole(), "BLOGGER")){
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }else{
//            Post post = new Post();
//            post.setPostTitle(postDto.getPostTitle());
//            post.setPostCategory(postDto.getPostCategory());
//            post.setPostBody(postDto.getPostBody());
//            postRepository.save(post);
//
//            return new ResponseEntity<>(postDto, HttpStatus.CREATED);
//        }

        Post post = new Post();
        post.setPostTitle(postDto.getPostTitle());
        post.setPostCategory(postDto.getPostCategory());
        post.setPostBody(postDto.getPostBody());
        postRepository.save(post);

        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PostDto> updatePost(PostDto postDto,
                                              long postId,
                                              long userId) {

        Optional<Users> users = usersRepository.findById(userId);
        if(users.isPresent() && Objects.equals(users.get().getUsersRole(), "BLOGGER")){
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()){
                post.get().setPostTitle(postDto.getPostTitle());
                post.get().setPostCategory(postDto.getPostCategory());
                post.get().setPostBody(postDto.getPostBody());
                post.get().setBlogger(users.get());
                postRepository.save(post.get());
                return new ResponseEntity<>(postDto, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<PostDto> viewAPost(String postTitle) {
        Optional<Post> selected = postRepository.findPostByPostTitle(postTitle);
        if(selected.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PostDto postDto1 = modelMapper.map(selected.get(), PostDto.class);
            return new ResponseEntity<>(postDto1, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<PostDto>> viewAllPost(int pageNo, int pageSize) {

        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        List<PostDto> postDtoList = new ArrayList<>();
        Page<Post> allPost =  postRepository.findAll(pageable);

        //get page content
        List<Post> postList = allPost.getContent();


        for (Post everyPost : allPost){
            postDtoList.add(modelMapper.map(everyPost, PostDto.class));
        }
        return new ResponseEntity<>(postDtoList, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Object> deletePost(long postId, long userId) {
        Optional<Users> blogger = usersRepository.findById(userId);
        if(blogger.isPresent() && Objects.equals(blogger.get().getUsersRole(), "BLOGGER")){
            Post post = (Post) postRepository.findPostByPostId(postId);
            if(post != null){
                postRepository.delete(post);
                return new ResponseEntity<>("deleted", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Only Admin can delete a post", HttpStatus.BAD_REQUEST);
    }
}
