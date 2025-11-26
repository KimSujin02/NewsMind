package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.mapper.UserMapper;
import com.newsapp.newsmind.security.JwtProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SettingFlowController {

    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;

}
