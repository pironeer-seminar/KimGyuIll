package com.example.demo1.Post.dto.request;

import com.example.demo1.User.entity.PostStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(description = "게시글 업데이트 요청 DTO")
public class PostUpdateReq {

    @NotBlank(message = "제목은 필수입니다.")
    @Schema(
            description = "게시글 제목",
            example = "만약 누가 이걸 본다면")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Schema(
            description = "게시글 내용",
            example = "오늘도 화이팅!")
    private String content;

    @NotNull(message = "상태는 필수입니다.")
    @Schema(
            description = "게시글 상태",
            example = "PUBLIC/PRIVATE")
    private PostStatus status;
}
