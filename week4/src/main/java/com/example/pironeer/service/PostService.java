package com.example.pironeer.service;

import com.example.pironeer.domain.Post;
import com.example.pironeer.domain.PostStatus;
import com.example.pironeer.domain.User;
import com.example.pironeer.dto.requset.PostCreateReq;
import com.example.pironeer.dto.requset.PostUpdateReq;
import com.example.pironeer.dto.response.CommentRes;
import com.example.pironeer.dto.response.PostDetailRes;
import com.example.pironeer.dto.response.PostSearchRes;
import com.example.pironeer.repository.PostRepository;
import com.example.pironeer.repository.UserRepository;
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
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

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


    public PostDetailRes detail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        List<CommentRes> commentResList = post.getComments().stream()
                .map(c -> new CommentRes(c.getId(), c.getUser().getId(), c.getContent()))
                .toList();

        return new PostDetailRes(post.getId(),
                post.getUser().getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                commentResList);
    }

    public Long update(Long postId, PostUpdateReq req) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        post.update(req.getTitle(), req.getContent(), req.getStatus());
        postRepository.save(post);

        return post.getId();
    }

    public Long delete(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }

    public List<PostSearchRes> getUserPostList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Post> posts = postRepository.findAllByUserId(userId);
        return posts.stream()
                .map(post -> new PostSearchRes(post.getUser().getId(), post.getId(), post.getTitle(), post.getContent(),
                        post.getCreatedAt()))
                .toList();
    }
}
