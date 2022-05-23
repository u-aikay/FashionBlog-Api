package com.aikay.fashionblog.repositories;

import com.aikay.fashionblog.models.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findPostByPostTitle(String postTile);
    Object findPostByPostId(Long postId);
}
//Pageable pageable,