package com.newsapp.newsmind.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String email;
    private String passwordHash;
    private String nickname;
    private String profileImage;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
    private String phone;
    private String birth; // yyyyMMdd 문자열로 가정
    private Boolean settingCompleted;
}