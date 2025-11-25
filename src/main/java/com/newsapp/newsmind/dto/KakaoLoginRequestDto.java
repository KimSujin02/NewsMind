package com.newsapp.newsmind.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoLoginRequestDto {
    private String kakaoUserId;    // 카카오 id
    private String email;          // 있을 수도 있고 없을 수도 있음
    private String nickname;
    private String profileImage;
    private String phone;
    private String birth;
    private String accessToken;
    private String refreshToken;
    private String tokenExpiresAt;
}
