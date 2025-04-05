package com.example.demo1.Common.exception.model;

import com.example.demo1.Common.exception.type.ErrorType;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(ErrorType errorType) {
        super(errorType, HttpStatus.NOT_FOUND);
    }
}
