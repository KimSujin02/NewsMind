package com.newsapp.newsmind.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CodeMapper {
    List<Map<String, Object>> selectCodeList(Map<String, Object> params);
}