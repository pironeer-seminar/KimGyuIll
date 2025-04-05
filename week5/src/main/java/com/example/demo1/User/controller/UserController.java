package com.example.demo1.User.controller;

import com.example.demo1.Common.dto.ApiRes;
import com.example.demo1.Common.type.UserSuccessType;
import com.example.demo1.User.dto.request.UserCreateReq;
import com.example.demo1.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ApiRes<?> create(@RequestBody UserCreateReq req) {
        userService.create(req);
        return ApiRes.success(UserSuccessType.CREATE);
    }

}
