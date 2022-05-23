package com.aikay.fashionblog.repositories;

import com.aikay.fashionblog.models.DislikeReactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DislikeReactionRepository extends JpaRepository<DislikeReactions, Long> {
}
