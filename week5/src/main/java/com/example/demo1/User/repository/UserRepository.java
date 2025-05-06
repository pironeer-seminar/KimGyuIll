package com.example.demo1.User.repository;

import com.example.demo1.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

