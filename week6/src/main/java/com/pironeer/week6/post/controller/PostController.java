package com.pironeer.week6.post.controller;

import com.pironeer.week6.post.dto.request.PostCreateReq;
import com.pironeer.week6.post.dto.request.PostUpdateReq;
import com.pironeer.week6.post.dto.response.PostRes;
import com.pironeer.week6.post.service.PostService;
import com.pironeer.week6.user.dto.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public void createPost(@RequestBody PostCreateReq req,
                           @AuthenticationPrincipal MemberPrincipal principal) {
        postService.create(req, principal.getUser());
    }

    @PutMapping("/{postId}")
    @PreAuthorize("@postGuard.check(#postId, principal)")
    public void updatePost(@PathVariable Long postId,
                           @RequestBody PostUpdateReq req,
                           @AuthenticationPrincipal MemberPrincipal principal) {
        postService.update(postId, req);
    }

    @GetMapping("/{postId}")
    public PostRes getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping
    public List<PostRes> getAllPosts() {
        return postService.getAllPosts();
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("@postGuard.check(#postId, principal)")
    public void deletePost(@PathVariable Long postId,
                           @AuthenticationPrincipal MemberPrincipal principal) {
        postService.delete(postId);
    }
}
