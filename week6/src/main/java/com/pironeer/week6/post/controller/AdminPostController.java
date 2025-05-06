package com.pironeer.week6.post.controller;

import com.pironeer.week6.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/posts")
public class AdminPostController {

    private final PostService postService;

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllPosts() {
        postService.deleteAllPosts();
    }

}
