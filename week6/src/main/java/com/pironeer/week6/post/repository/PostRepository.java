package com.pironeer.week6.post.repository;

import com.pironeer.week6.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
