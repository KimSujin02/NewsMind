package com.newsapp.newsmind.service;

import com.newsapp.newsmind.mapper.UserSourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserSourceService {

    private final UserSourceMapper userSourceMapper;

    public Map<String, Object> insertUserSource(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        int result = userSourceMapper.insertUserSource(params);
        if(result >= 1) {
            resultParams.put("resultMsg", "Success");
        }  else {
            resultParams.put("resultMsg", "Fail");
        }
        return resultParams;
    }

    public Map<String, Object> deleteUserSource(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        int result = userSourceMapper.deleteUserSource(params);
        if(result >= 1) {
            resultParams.put("resultMsg", "Success");
        }  else {
            resultParams.put("resultMsg", "Fail");
        }
        return resultParams;
    }

    public List<Map<String, Object>> selectUserSourceList(Map<String, Object> params) {
        return userSourceMapper.selectUserSourceList(params);
    }
}