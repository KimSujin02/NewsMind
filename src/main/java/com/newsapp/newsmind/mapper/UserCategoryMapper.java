package com.newsapp.newsmind.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserCategoryMapper {
    int insertUserCategory(Map<String, Object> params);
    int deleteAllUserCategory(Map<String, Object> params);
    List<Map<String, Object>> selectUserCategoryList(Map<String, Object> params);
}
