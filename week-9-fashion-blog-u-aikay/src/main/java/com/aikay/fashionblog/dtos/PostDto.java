package com.aikay.fashionblog.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDto {
    private String postTitle;
    private String postCategory;
    private String postBody;
}