package com.pironeer.week6.user.service;

import com.pironeer.week6.global.jwt.JWTUtil;
import com.pironeer.week6.user.dto.request.UserJoinReq;
import com.pironeer.week6.user.dto.request.UserLoginReq;
import com.pironeer.week6.user.dto.response.UserJoinRes;
import com.pironeer.week6.user.dto.response.UserLoginRes;
import com.pironeer.week6.user.entity.User;
import com.pironeer.week6.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.pironeer.week6.user.entity.User.Role.ADMIN;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public UserJoinRes join(UserJoinReq req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(req.getPassword());

        User user = User.builder()
                .email(req.getEmail())
                .name(req.getName())
                .password(encodedPassword)
                .role(User.Role.USER)
                .build();

        User saved = userRepository.save(user);
        return UserJoinRes.from(saved);
    }

    public UserLoginRes login(UserLoginReq req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일이 존재하지 않습니다."));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createAccessToken(
                user.getId(), user.getEmail(), user.getRole().name()
        );

        String refreshToken = jwtUtil.createRefreshToken(
                user.getId(), user.getEmail(), user.getRole().name()
        );

        return UserLoginRes.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    public UserJoinRes adminJoin(UserJoinReq req) {
        if (userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .name(req.getName())
                .role(User.Role.ADMIN) // ✅ 관리자 권한 부여
                .build();

        userRepository.save(user);
        return UserJoinRes.builder()
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
