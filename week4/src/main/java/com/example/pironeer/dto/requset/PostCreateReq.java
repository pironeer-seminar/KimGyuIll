package com.example.pironeer.dto.requset;

import com.example.pironeer.domain.PostStatus;
import lombok.Getter;

@Getter
public class PostCreateReq {

    private Long userId;

    private String title;

    private String content;

    private PostStatus status;
}
