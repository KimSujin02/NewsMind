package com.newsapp.newsmind.mapper;

import com.newsapp.newsmind.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {
    int insertUser(UserDto user);
    int updateSettingCompleted(@Param("userId") Long userId);
    UserDto findByUserId(Long userId);
    void updateLastLoginAt(@Param("userId") Long userId,
                           @Param("lastLoginAt") LocalDateTime lastLoginAt);
}