package com.example.demo1.Post.controller;

import com.example.demo1.Common.dto.ApiRes;
import com.example.demo1.Common.type.PostSuccessType;
import com.example.demo1.Post.dto.request.PostCreateReq;
import com.example.demo1.Post.dto.request.PostUpdateReq;
import com.example.demo1.Post.dto.response.PostSearchRes;
import com.example.demo1.Post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 생성
    @PostMapping("")
    @Operation(
            summary = "게시글 생성",
            description = "사용자 ID, 제목, 내용, 상태를 기반으로 새 게시글을 생성합니다.")
    public ApiRes<?> create(@Valid @RequestBody PostCreateReq req) {
        postService.create(req);
        return ApiRes.success(PostSuccessType.CREATE);
    }

    // 목록조회
    @GetMapping("")
    @Operation(
            summary = "PUBLIC 게시글 목록 조회",
            description = "전체 공개(PUBLIC) 게시글 목록을 조회합니다."
    )
    public ApiRes<List<PostSearchRes>> search() {
        return ApiRes.success(PostSuccessType.GET_ALL, postService.search());
    }

    // 단일조회
    @GetMapping("/{postId}")
    @Operation(
            summary = "게시글 단일 조회",
            description = "게시글 ID를 이용해 특정 게시글 정보를 조회합니다."
    )
    public ApiRes<PostSearchRes> detail(
            @PathVariable("postId") Long postId
    ) {
        return ApiRes.success(PostSuccessType.GET_POST, postService.detail(postId));
    }

    // 수정
    @PutMapping("/{postId}")
    @Operation(
            summary = "게시글 수정",
            description = "게시글 ID를 이용하여 게시글을 수정합니다."
    )
    public ApiRes<Long> update(
            @PathVariable("postId") Long postId,
            @Valid @RequestBody PostUpdateReq req) {
        return ApiRes.success(PostSuccessType.UPDATE, postService.update(postId, req));
    }

    // 삭제
    @DeleteMapping("/{postId}")
    @Operation(
            summary = "게시글 삭제",
            description = "게시글 ID를 이용하여 게시글을 삭제합니다."
    )
    public ApiRes<Long> delete(
            @PathVariable("postId") Long postId
    ) {
        return ApiRes.success(PostSuccessType.DELETE, postService.delete(postId));
    }
}
