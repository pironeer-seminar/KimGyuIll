package com.example.demo1.Post.dto.request;

import com.example.demo1.User.entity.PostStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Schema(description = "게시글 생성 요청 DTO")
public class PostCreateReq {

    @NotNull
    @Schema(
            description = "게시글 작성자 ID",
            example = "1")
    private Long userId;

    @NotBlank(message = "제목은 필수입니다.")
    @Schema(
            description = "게시글 제목",
            example = "그거 아세요?")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Schema(
            description = "게시글 내용",
            example = "벌써 4월이라는 거 ㅜㅜ")
    private String content;

    @NotNull(message = "상태는 필수입니다.")
    @Schema(
            description = "게시글 상태",
            example = "PUBLIC/PRIVATE")
    private PostStatus status;
}
