package com.pironeer.week2.controller;

import com.pironeer.week2.dto.AnalyzeRequestDto;
import com.pironeer.week2.dto.AnalyzeResponseDto;
import com.pironeer.week2.service.AnalyzeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze")
public class AnalyzeController {
    private AnalyzeService analyzeService;

    public AnalyzeController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @PostMapping
    public ResponseEntity<AnalyzeResponseDto> analyze( @Valid @RequestBody AnalyzeRequestDto analyzeRequestDto) {
        AnalyzeResponseDto response = analyzeService.analyzeSentence(analyzeRequestDto);
        return ResponseEntity.ok(response);
    }
}
