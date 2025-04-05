package com.example.demo1.Common.type;

public enum CommonErrorType implements ErrorType {

    INTERNAL_SERVER("Common1", "Internal Server Error"),
    BAD_REQUEST("Common2", "잘못된 요청입니다."),;

    private final String code;
    private final String message;

    CommonErrorType(String code, String message) {
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