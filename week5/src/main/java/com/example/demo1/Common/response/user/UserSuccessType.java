package com.example.demo1.Common.response.user;

import com.example.demo1.Common.response.type.SuccessType;

public enum UserSuccessType implements SuccessType {
    CREATE("USER_1", "유저 생성에 성공했습니다.");

    private final String code;
    private final String message;

    UserSuccessType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
