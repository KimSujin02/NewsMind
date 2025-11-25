package com.newsapp.newsmind.service;

import com.newsapp.newsmind.mapper.UserKeywordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserKeywordService {

    private final UserKeywordMapper userKeywordMapper;

    public Map<String, Object> insertUserKeyword(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        String keywordId = userKeywordMapper.selectNextKeywordId();
        params.put("keywordId", keywordId);

        int result = userKeywordMapper.insertUserKeyword(params);
        if(result >= 1) {
            resultParams.put("resultMsg", "Success");
        }  else {
            resultParams.put("resultMsg", "Fail");
        }
        return resultParams;
    }

    public Map<String, Object> deleteUserKeyword(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        int result = userKeywordMapper.deleteUserKeyword(params);
        if(result >= 1) {
            resultParams.put("resultMsg", "Success");
        }  else {
            resultParams.put("resultMsg", "Fail");
        }
        return resultParams;
    }

    public List<Map<String, Object>> selectUserKeywordList(Map<String, Object> params) {
        return userKeywordMapper.selectUserKeywordList(params);
    }
}