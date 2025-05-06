package com.pironeer.week6.user.controller;

import com.pironeer.week6.global.jwt.JWTUtil;
import com.pironeer.week6.user.dto.request.UserJoinReq;
import com.pironeer.week6.user.dto.request.UserLoginReq;
import com.pironeer.week6.user.dto.response.UserJoinRes;
import com.pironeer.week6.user.dto.response.UserLoginRes;
import com.pironeer.week6.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<UserJoinRes> signup(@RequestBody UserJoinReq req) {
        UserJoinRes res = userService.join(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginRes> login(@RequestBody UserLoginReq req) {
        UserLoginRes res = userService.login(req);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/reissue")
    public ResponseEntity<UserLoginRes> reissue(@RequestHeader("Authorization") String refreshToken) {
        if (refreshToken == null || !jwtUtil.isRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
        }

        if (!jwtUtil.validateToken(refreshToken)) {
            throw new IllegalArgumentException("리프레시 토큰 만료 혹은 위조됨");
        }

        String email = jwtUtil.getUserId(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        String newAccessToken = jwtUtil.createAccessToken(null, email, role);

        return ResponseEntity.ok(UserLoginRes.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build());
    }

    @PostMapping("/admin-signup")
    public ResponseEntity<UserJoinRes> adminSignup(@RequestBody UserJoinReq req) {
        UserJoinRes res = userService.adminJoin(req);
        return ResponseEntity.ok(res);
    }
}
