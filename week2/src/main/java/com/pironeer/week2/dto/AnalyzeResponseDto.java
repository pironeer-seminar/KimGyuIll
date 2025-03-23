package com.pironeer.week2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnalyzeResponseDto {
    private int length;
    private int wordCount;
    private boolean containSpring;
}
