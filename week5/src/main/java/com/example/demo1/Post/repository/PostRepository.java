package com.example.demo1.Post.repository;

import com.example.demo1.Post.entity.Post;
import com.example.demo1.User.entity.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByStatus(PostStatus status);
}
