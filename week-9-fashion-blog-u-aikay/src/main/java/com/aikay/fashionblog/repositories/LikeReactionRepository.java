package com.aikay.fashionblog.repositories;

import com.aikay.fashionblog.models.LikeReactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeReactionRepository extends JpaRepository<LikeReactions, Long> {
}
