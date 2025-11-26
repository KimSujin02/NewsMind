package com.newsapp.newsmind.service;

import com.newsapp.newsmind.mapper.UserCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCategoryService {

    private final UserCategoryMapper userCategoryMapper;

    public Map<String, Object> updateUserCategory(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        userCategoryMapper.deleteAllUserCategory(params);
        List<String> categories = ((List<?>) params.get("categories"))
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
        int priorityOrd = 1;
        int result = 0;
        for (String category : categories) {
            params.put("categoryCode", category);
            params.put("priorityOrd", priorityOrd++);
            result += userCategoryMapper.insertUserCategory(params);
        }

        if(result == categories.size()) {
            resultParams.put("resultMsg", "Success");
        }  else {
            resultParams.put("resultMsg", "Fail");
        }
        return resultParams;
    }

    public List<Map<String, Object>> selectUserCategoryList(Map<String, Object> params) {
        return userCategoryMapper.selectUserCategoryList(params);
    }
}