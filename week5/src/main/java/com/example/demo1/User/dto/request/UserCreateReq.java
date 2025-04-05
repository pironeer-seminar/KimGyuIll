package com.example.demo1.User.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Schema(description = "유저 생성 요청 DTO")
public class UserCreateReq {

    @NotBlank
    @Schema(
            description = "User 이름",
            example = "김규일"
    )
    private String name;
}
