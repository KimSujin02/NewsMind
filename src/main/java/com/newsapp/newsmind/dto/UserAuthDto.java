package com.newsapp.newsmind.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
public class UserAuthDto {
    private Long authId;
    private Long userId;
    private String provider;  // kakao
    private String providerUserId;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime connectedAt;
}