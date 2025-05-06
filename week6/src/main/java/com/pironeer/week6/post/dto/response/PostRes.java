package com.pironeer.week6.post.dto.response;

import com.pironeer.week6.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostRes {
    private final Long postId;
    private final String title;
    private final String content;
    private final Long userId;
    private final String userEmail;
    private final LocalDateTime createdAt;

    public static PostRes from(Post post) {
        return PostRes.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getAuthor().getId())
                .build();
    }
}
