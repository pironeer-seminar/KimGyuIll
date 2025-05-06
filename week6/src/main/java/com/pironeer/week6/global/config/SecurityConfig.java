package com.pironeer.week6.global.config;

import com.pironeer.week6.global.jwt.JwtAuthFilter;
import com.pironeer.week6.user.service.MemberDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final MemberDetailsService memberDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(MemberDetailsService memberDetailsService, JwtAuthFilter jwtAuthFilter) {
        this.memberDetailsService = memberDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(it -> it.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").authenticated()
                        .anyRequest().permitAll()
                )
                .userDetailsService(memberDetailsService)
                .formLogin(form -> form
                        .loginPage("/login.html")           // GET 로그인 페이지 (정적 파일)
                        .loginProcessingUrl("/login")       // POST 처리 URL
                        .defaultSuccessUrl("/admin", true)       // 성공 후 이동
                        .failureUrl("/login.html?error")    // 실패 시
                        .permitAll())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean              // BCrypt 해시 사용
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
