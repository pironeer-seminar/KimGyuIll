package com.example.pironeer.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public String email;

    protected User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
