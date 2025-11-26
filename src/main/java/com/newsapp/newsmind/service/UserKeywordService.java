package com.newsapp.newsmind.service;

import com.newsapp.newsmind.dto.UserDto;
import com.newsapp.newsmind.mapper.UserKeywordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserKeywordService {

    private final UserKeywordMapper userKeywordMapper;

    public Map<String, Object> updateUserKeyword(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        userKeywordMapper.deleteAllUserKeyword(params);

        List<String> keywords = ((List<?>) params.get("keywords"))
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toList());

        int result = 0;
        for (String keyword : keywords) {
            params.put("keyword", keyword);
            result += userKeywordMapper.insertUserKeyword(params);
        }

        if(result == keywords.size()) {
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