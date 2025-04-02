package com.example.pironeer.controller;

import com.example.pironeer.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{userId}")
    public void like(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        likeService.like(userId, postId);
    }

    @DeleteMapping("/{userId}")
    public void unlike(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        likeService.unlike(userId, postId);
    }

    @GetMapping("/count")
    public long count(@PathVariable("postId") Long postId) {
        return likeService.countLikes(postId);
    }

    @GetMapping("/{userId}")
    public boolean isLiked(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) {
        return likeService.isLiked(userId, postId);
    }
}

