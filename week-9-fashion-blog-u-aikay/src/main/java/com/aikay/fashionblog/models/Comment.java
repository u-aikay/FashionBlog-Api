package com.aikay.fashionblog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentTitle;
    private String comment;
    @JsonIgnore
    @ManyToOne
    private Users reader;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private Post post;

    public Comment(String commentTitle, String comment) {
        this.commentTitle = commentTitle;
        this.comment = comment;
    }
}
