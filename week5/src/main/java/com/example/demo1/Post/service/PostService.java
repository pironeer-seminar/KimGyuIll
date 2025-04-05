package com.example.demo1.Post.service;

import com.example.demo1.Common.exception.NotFoundException;
import com.example.demo1.Common.type.PostErrorType;
import com.example.demo1.Common.type.UserErrorType;
import com.example.demo1.Post.dto.request.PostCreateReq;
import com.example.demo1.Post.dto.request.PostUpdateReq;
import com.example.demo1.Post.dto.response.PostSearchRes;
import com.example.demo1.Post.entity.Post;
import com.example.demo1.User.entity.PostStatus;
import com.example.demo1.User.entity.User;
import com.example.demo1.Post.repository.PostRepository;
import com.example.demo1.User.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long create(PostCreateReq req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundException(UserErrorType.NOT_FOUND));

        Post post = Post.create(user, req.getTitle(), req.getContent(), req.getStatus());
        post = postRepository.save(post);

        return post.getId();
    }

    public List<PostSearchRes> search() {
        // PostStatus가 public인 게시글만 조회할 수 있다.
        List<Post> posts = postRepository.findAllByStatus(PostStatus.PUBLIC);
        return posts.stream()
                .map(post ->
                        new PostSearchRes(post.getUser().getId(), post.getId(), post.getTitle(),
                                post.getContent(), post.getCreatedAt())
                )
                .toList();
    }

    public PostSearchRes detail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(PostErrorType.NOT_FOUND));

        return new PostSearchRes(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent(),
                post.getCreatedAt());
    }

    public Long update(Long postId, PostUpdateReq req) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(PostErrorType.NOT_FOUND));

        post.update(req.getTitle(), req.getContent(), req.getStatus());
        postRepository.save(post);

        return post.getId();
    }

    public Long delete(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }
}
