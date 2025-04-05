package com.example.demo1.Common.exception;

import com.example.demo1.Common.dto.ApiRes;
import com.example.demo1.Common.type.CommonErrorType;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j // 로그가 에쁘게 나옴
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiRes<?>> handleIllegalArgumentException(final IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(
                ApiRes.fail(CommonErrorType.INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // @RequestParam 유효성 검사 예외처리
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiRes<?> handleConstraintViolationException(ConstraintViolationException ex) {
        return ApiRes.fail(CommonErrorType.BAD_REQUEST, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // @RequestBody 유효성 검사 예외처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiRes<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(
                ApiRes.fail(CommonErrorType.BAD_REQUEST, HttpStatus.BAD_REQUEST, Map.of("fieldErrors", fieldErrors)),
                HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiRes<?>> handleCustomException(BaseException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ApiRes.fail(ex), ex.getHttpStatus());
    }

    // 그 외 예외처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiRes<?>> handleUnexpectedException(Exception ex) {
        return new ResponseEntity<>(
                ApiRes.fail(CommonErrorType.INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR, "예기치 못한 오류가 발생했습니다."),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
