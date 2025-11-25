package com.newsapp.newsmind.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserCountryMapper {
    int insertUserCountry(Map<String, Object> params);
    int updatePrimaryUserCountryBefore(Map<String, Object> params);
    int updatePrimaryUserCountry(Map<String, Object> params);
    int deleteUserCountry(Map<String, Object> params);
    int deleteAllUserCountry(Map<String, Object> params);
    List<Map<String, Object>> selectUserCountryList(Map<String, Object> params);
}