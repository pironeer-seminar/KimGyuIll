package com.example.demo1.Post.controller;

import com.example.demo1.Common.dto.ApiRes;
import com.example.demo1.Common.type.PostSuccessType;
import com.example.demo1.Post.dto.request.PostCreateReq;
import com.example.demo1.Post.dto.request.PostUpdateReq;
import com.example.demo1.Post.dto.response.PostSearchRes;
import com.example.demo1.Post.service.PostService;
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
    public ApiRes<?> create(@RequestBody PostCreateReq req) {
        postService.create(req);
        return ApiRes.success(PostSuccessType.CREATE);
    }

    // 목록조회
    @GetMapping("")
    public ApiRes<List<PostSearchRes>> search() {
        return ApiRes.success(PostSuccessType.GET_ALL, postService.search());
    }

    // 단일조회
    @GetMapping("/{postId}")
    public ApiRes<PostSearchRes> detail(
            @PathVariable("postId") Long postId
    ) {
        return ApiRes.success(PostSuccessType.GET_POST, postService.detail(postId));
    }

    // 수정
    @PutMapping("/{postId}")
    public ApiRes<Long> update(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateReq req) {
        return ApiRes.success(PostSuccessType.UPDATE, postService.update(postId, req));
    }

    // 삭제
    @DeleteMapping("/{postId}")
    public ApiRes<Long> delete(
            @PathVariable("postId") Long postId
    ) {
        return ApiRes.success(PostSuccessType.DELETE, postService.delete(postId));
    }
}
