package com.pironeer.week6.user.dto.response;

import com.pironeer.week6.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserJoinRes {
    private final Long id;
    private final String email;
    private final String name;

    @Builder
    public UserJoinRes(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static UserJoinRes from(User user) {
        return UserJoinRes.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}