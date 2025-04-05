package com.example.pironeer.repository;

import com.example.pironeer.domain.Post;
import com.example.pironeer.domain.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByStatus(PostStatus status);

    List<Post> findAllByUserId(Long userId);
}
