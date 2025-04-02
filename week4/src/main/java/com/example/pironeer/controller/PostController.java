package com.example.pironeer.controller;

import com.example.pironeer.dto.requset.PostCreateReq;
import com.example.pironeer.dto.requset.PostUpdateReq;
import com.example.pironeer.dto.response.PostSearchRes;
import com.example.pironeer.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public Long create(@RequestBody PostCreateReq req) {
        return postService.create(req);
    }

    // 목록 조회
    @GetMapping()
    public List<PostSearchRes> search() {
        return postService.search();
    }

    // 단일 조회
    @GetMapping("/{postId}")
    public PostSearchRes detail(
            @PathVariable("postId") Long postId
    ) {
        return postService.detail(postId);
    }

    // 수정
    @PutMapping("/{postId}")
    public Long update(
            @PathVariable("postId") Long postId,
            @RequestBody PostUpdateReq req) {
        return postService.update(postId, req);
    }

    // 삭제
    @DeleteMapping("/{postId}")
    public Long delete(@PathVariable("postId") Long postId) {
        return postService.delete(postId);
    }

    @GetMapping("/user/{userId}")
    public List<PostSearchRes> userSearch(@PathVariable("userId") Long userId) {
        return postService.userPostList(userId);
    }
}
