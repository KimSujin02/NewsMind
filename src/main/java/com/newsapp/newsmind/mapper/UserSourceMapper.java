package com.newsapp.newsmind.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserSourceMapper {
    int insertUserSource(Map<String, Object> params);
    int deleteUserSource(Map<String, Object> params);
    List<Map<String, Object>> selectUserSourceList(Map<String, Object> params);
}
