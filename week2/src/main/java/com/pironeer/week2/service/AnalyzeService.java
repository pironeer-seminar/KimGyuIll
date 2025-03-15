package com.pironeer.week2.service;

import com.pironeer.week2.dto.AnalyzeRequestDto;
import com.pironeer.week2.dto.AnalyzeResponseDto;
import org.springframework.stereotype.Service;

@Service
public class AnalyzeService {
    public AnalyzeResponseDto analyzeSentence(AnalyzeRequestDto analyzeRequestDto) {
        String sentence = analyzeRequestDto.getSentence();
        int length = sentence.length();
        int wordCount = sentence.split(" ").length;
        boolean containSpring = sentence.toLowerCase().contains("spring");

        return new AnalyzeResponseDto(length, wordCount, containSpring);
    }
}
