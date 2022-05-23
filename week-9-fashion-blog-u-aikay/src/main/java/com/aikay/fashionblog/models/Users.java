package com.aikay.fashionblog.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "first_name",nullable = false)
    private String usersFirstName;
    @Column(name = "last_name",nullable = false)
    private String usersLastName;
    @Column(name = "email",nullable = false)
    private String usersEmail;
    @Column(name = "password",nullable = false)
    private String usersPassword;
    @Column(name = "confirm_password",nullable = false)
    private String usersConfirmPassword;
    @Column(nullable = false)
    private String usersRole;

    @OneToMany(mappedBy = "blogger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> bloggerPost;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Users (String usersFirstName, String usersLastName, String usersEmail, String usersPassword, String usersConfirmPassword) {
        this.usersFirstName = usersFirstName;
        this.usersLastName = usersLastName;
        this.usersEmail = usersEmail;
        this.usersPassword = usersPassword;
        this.usersConfirmPassword = usersConfirmPassword;
    }
}
