package com.example.pironeer.repository;

import com.example.pironeer.domain.Like;
import com.example.pironeer.domain.Post;
import com.example.pironeer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByUserAndPost(User user, Post post);

    Optional<Like> findByUserAndPost(User user, Post post);

    long countByPost(Post post);

}
