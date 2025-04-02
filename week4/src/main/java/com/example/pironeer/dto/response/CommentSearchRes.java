package com.example.pironeer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentSearchRes {
    private Long commentId;
    private Long userId;
    private Long postId;
    private String content;
}
