package com.aikay.fashionblog.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LikeReactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long likeReactionId;
    private long postId;
    private long commentId;
    private long userId;
}
