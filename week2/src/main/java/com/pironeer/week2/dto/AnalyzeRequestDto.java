package com.pironeer.week2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalyzeRequestDto {
    @NotBlank(message = "문장은 필수 입력 값입니다.")
    private String sentence;
}
