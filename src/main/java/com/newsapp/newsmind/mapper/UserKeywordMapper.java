package com.newsapp.newsmind.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserKeywordMapper {
    int insertUserKeyword(Map<String, Object> params);
    int deleteAllUserKeyword(Map<String, Object> params);
    List<Map<String, Object>> selectUserKeywordList(Map<String, Object> params);
}
