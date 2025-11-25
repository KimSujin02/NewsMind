package com.newsapp.newsmind.mapper;

import com.newsapp.newsmind.dto.UserAuthDto;
import com.newsapp.newsmind.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAuthMapper {
    int insertUserAuth(UserAuthDto userAuth);
    UserAuthDto findByProviderUserId(@Param("providerUserId") String kakaoId);
    int updateTokens(UserAuthDto userAuth);
}