package com.example.demo1.Post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostSearchRes {

    @NotNull
    @Schema(
            description = "게시글 작성자 ID",
            example = "1"
    )
    private Long userId;

    @NotNull
    @Schema(
            description = "게시글 ID",
            example = "1"
    )
    private Long postId;

    @NotBlank
    @Schema(
            description = "게시글 제목",
            example = "현재 시간 22시  8분"
    )
    private String title;

    @NotBlank
    @Schema(
            description = "게시글 내용",
            example = "자정 전에 자는게 목표"
    )
    private String content;

    @NotBlank
    @Schema(
            description = "게시글 생성 시간",
            example = "2025-04-05T15:30:45.123"
    )
    private LocalDateTime createdAt;
}
