package com.aikay.fashionblog.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginDto {
    private String usersEmail;
    private String usersPassword;
}
