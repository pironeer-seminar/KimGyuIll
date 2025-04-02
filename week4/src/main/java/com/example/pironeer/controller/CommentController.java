package com.example.pironeer.controller;

import com.example.pironeer.dto.requset.CommentCreateReq;
import com.example.pironeer.dto.requset.CommentUpdateReq;
import com.example.pironeer.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public Long create(@PathVariable("postId") Long postId, @RequestBody CommentCreateReq req) {
        return commentService.create(postId, req);

    }

    // 수정
    @PutMapping("/{postId}/{commentId}")
    public Long update(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentUpdateReq req) {
        return commentService.update(postId, commentId, req.getContent());
    }

    // 삭제
    @DeleteMapping("/{postId}/{commentId}")
    public Long delete(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        return commentService.delete(postId, commentId);
    }


}
