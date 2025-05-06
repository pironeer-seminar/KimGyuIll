package com.pironeer.week6.user.dto.request;

import lombok.Getter;

@Getter
public class UserLoginReq {
    private String email;
    private String password;
}
