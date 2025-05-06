package com.pironeer.week6.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLoginRes {
    private final String accessToken;
    private final String refreshToken;

    @Builder
    public UserLoginRes(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}