package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.dto.UserDto;
import com.newsapp.newsmind.mapper.UserMapper;
import com.newsapp.newsmind.security.JwtProvider;
import com.newsapp.newsmind.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {

    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<?> getLoginUser(HttpServletRequest request) {
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if ("token".equals(c.getName())) {
                    token = c.getValue();
                    break;
                }
            }
        }
        if (token == null) {
            return ResponseEntity.status(401).body("UNAUTHORIZED");
        }
        Long userId = jwtProvider.getUserIdFromToken(token);
        if (userId == null) {
            return ResponseEntity.status(401).body("UNAUTHORIZED");
        }
        UserDto user = userMapper.findByUserId(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);  // 쿠키 삭제
        response.addCookie(cookie);

        return ResponseEntity.ok().body("LOGOUT");
    }

}