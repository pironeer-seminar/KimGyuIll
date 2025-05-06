package com.example.demo1.Common.response.user;

import com.example.demo1.Common.exception.type.ErrorType;

public enum UserErrorType implements ErrorType {

    NOT_FOUND("USER_1", "조회된 유저가 없습니다.");

    private final String code;
    private final String message;

    UserErrorType(String code, String message) {
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
