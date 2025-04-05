package com.example.demo1.Post.dto.request;

import com.example.demo1.User.entity.PostStatus;
import lombok.Getter;

@Getter
public class PostCreateReq {

    private Long userId;

    private String title;

    private String content;

    private PostStatus status;
}
