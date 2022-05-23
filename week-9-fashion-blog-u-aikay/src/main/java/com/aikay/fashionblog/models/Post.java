package com.aikay.fashionblog.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "post_title", nullable = false)
    private String postTitle;
    @Column(name = "post_category", nullable = false)
    private String postCategory;
    @Column(name = "post_body", nullable = false)
    private String postBody;
    @ManyToOne
    private Users blogger;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    private List<Comment> comment;

//    public Post(String postTitle, String postCategory) {
//        this.postTitle = postTitle;
//        this.postCategory = postCategory;
//    }
}
