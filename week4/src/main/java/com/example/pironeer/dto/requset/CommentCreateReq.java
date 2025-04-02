package com.example.pironeer.dto.requset;

import lombok.Getter;

@Getter
public class CommentCreateReq {
    private Long userId;
    private String content;

}
