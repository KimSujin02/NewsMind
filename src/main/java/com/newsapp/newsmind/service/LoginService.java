package com.newsapp.newsmind.service;

import com.newsapp.newsmind.client.KakaoClient;
import com.newsapp.newsmind.dto.KakaoUserInfoDto;
import com.newsapp.newsmind.dto.UserAuthDto;
import com.newsapp.newsmind.dto.UserDto;
import com.newsapp.newsmind.mapper.UserAuthMapper;
import com.newsapp.newsmind.mapper.UserMapper;
import com.newsapp.newsmind.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final KakaoClient kakaoClient;
    private final UserMapper userMapper;
    private final UserAuthMapper userAuthMapper;
    private final JwtProvider jwtProvider;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    public ResponseEntity<?> createKakaoLoginUrl() {
        String url = "https://kauth.kakao.com/oauth/authorize"
                + "?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectUri;

        return ResponseEntity.status(302)
                .header("Location", url)
                .build();
    }

    @Data
    @AllArgsConstructor
    public static class LoginResult {
        private UserDto user;
        private String jwt;
    }

    public LoginResult kakaoLoginProcess(String code) {

        // 1) Access Token 가져오기 (단 1번!)
        String accessToken = kakaoClient.getAccessToken(code, clientId, redirectUri);

        // 2) 카카오 사용자 정보 조회
        KakaoUserInfoDto kakaoInfo = kakaoClient.getUserInfo(accessToken);

        // 3) user_auth 조회
        UserAuthDto auth = userAuthMapper.findByProviderUserId(kakaoInfo.getId());

        Long userId;

        if (auth == null) {
            // ---------------- 신규 유저 ---------------- //
            UserDto user = new UserDto();
            user.setEmail(kakaoInfo.getEmail());
            user.setNickname(kakaoInfo.getNickname());
            user.setSettingCompleted(false);

            userMapper.insertUser(user);
            userId = user.getUserId();

            // user_auth 저장
            UserAuthDto newAuth = new UserAuthDto();
            newAuth.setUserId(userId);
            newAuth.setProvider("kakao");
            newAuth.setProviderUserId(kakaoInfo.getId());
            newAuth.setAccessToken(accessToken);

            userAuthMapper.insertUserAuth(newAuth);

        } else {
            // ---------------- 기존 유저 ---------------- //
            userId = auth.getUserId();
            userMapper.updateLastLoginAt(userId, LocalDateTime.now());
        }

        UserDto user = userMapper.findByUserId(userId);

        // JWT 발급
        String jwt = jwtProvider.createToken(userId);

        return new LoginResult(user, jwt);
    }
}

