package com.aikay.fashionblog.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String usersFirstName;
    private String usersLastName;
    private String usersEmail;
    private String usersPassword;
    private String usersConfirmPassword;
}
