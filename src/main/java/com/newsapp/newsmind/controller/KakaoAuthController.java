package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.dto.UserDto;
import com.newsapp.newsmind.mapper.UserMapper;
import com.newsapp.newsmind.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/kakao")
public class KakaoAuthController {

    private final LoginService loginService;

    @GetMapping("/login-url")
    public ResponseEntity<?> redirectToKakao() {
        return loginService.createKakaoLoginUrl();
    }

    @GetMapping("/callback")
    public ResponseEntity<?> kakaoCallback(@RequestParam("code") String code,
                                           HttpServletResponse response) {

        LoginService.LoginResult result = loginService.kakaoLoginProcess(code);

        // JWT 쿠키 저장
        Cookie cookie = new Cookie("token", result.getJwt());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);

        // 최초 로그인 → 설정 페이지
        if (!Boolean.TRUE.equals(result.getUser().getSettingCompleted())) {
            response.setHeader("Location", "/settings/initial");
            return ResponseEntity.status(302).build();
        }

        // 기존 유저 → 뉴스 피드
        response.setHeader("Location", "/news/feed");
        return ResponseEntity.status(302).build();
    }
}
