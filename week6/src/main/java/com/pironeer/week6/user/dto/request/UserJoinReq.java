package com.pironeer.week6.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinReq {
    private String email;
    private String name;
    private String password;
}
