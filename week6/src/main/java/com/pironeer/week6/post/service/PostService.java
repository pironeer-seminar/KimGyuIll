package com.pironeer.week6.post.service;

import com.pironeer.week6.post.dto.request.PostCreateReq;
import com.pironeer.week6.post.dto.request.PostUpdateReq;
import com.pironeer.week6.post.dto.response.PostRes;
import com.pironeer.week6.post.entity.Post;
import com.pironeer.week6.post.repository.PostRepository;
import com.pironeer.week6.user.entity.User;
import com.pironeer.week6.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void create(PostCreateReq req, User user) {
        Post post = Post.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .author(user)
                .build();

        postRepository.save(post);
    }


    @Transactional
    public void update(Long postId, PostUpdateReq req) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        post.update(req.getTitle(), req.getContent());
    }

    @Transactional(readOnly = true)
    public PostRes getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return PostRes.from(post);
    }

    @Transactional(readOnly = true)
    public List<PostRes> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostRes::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        postRepository.delete(post);
    }

    @Transactional
    public void deleteAllPosts() {
        postRepository.deleteAll();
    }

}
