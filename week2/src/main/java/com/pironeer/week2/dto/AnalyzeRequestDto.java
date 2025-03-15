package com.pironeer.week2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalyzeRequestDto {
    @NotBlank
    private String sentence;
}
