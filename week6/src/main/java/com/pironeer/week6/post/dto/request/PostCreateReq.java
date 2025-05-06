package com.pironeer.week6.post.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostCreateReq {
    private final String title;
    private final String content;
}
