package com.example.pironeer.service;

import com.example.pironeer.domain.Comment;
import com.example.pironeer.domain.Post;
import com.example.pironeer.domain.User;
import com.example.pironeer.dto.requset.CommentCreateReq;
import com.example.pironeer.dto.response.CommentSearchRes;
import com.example.pironeer.repository.CommentRepository;
import com.example.pironeer.repository.PostRepository;
import com.example.pironeer.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public Long create(Long postId, CommentCreateReq req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment comment = Comment.create(user, post, req.getContent());
        comment = commentRepository.save(comment);

        return comment.getId();
    }


    public Long delete(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if (!comment.getPost().getId().equals(postId)) {
            throw new IllegalArgumentException("Post ID mismatch for this comment");
        }

        commentRepository.delete(comment);
        return commentId;
    }


    public Long update(Long postId, Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        comment.update(content);
        commentRepository.save(comment);

        return comment.getId();

    }

    public List<CommentSearchRes> search() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(comment ->
                        new CommentSearchRes(comment.getId(),
                                comment.getUser().getId(),
                                comment.getPost().getId(),
                                comment.getContent())
                )
                .toList();
    }
}
