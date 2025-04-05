package com.example.pironeer.dto.requset;

import com.example.pironeer.domain.PostStatus;
import lombok.Getter;

@Getter
public class PostUpdateReq {
    public String title;

    private String content;

    private PostStatus status;

}
