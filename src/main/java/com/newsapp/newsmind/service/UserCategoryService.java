package com.newsapp.newsmind.service;

import com.newsapp.newsmind.mapper.UserCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserCategoryService {

    private final UserCategoryMapper userCategoryMapper;

    public Map<String, Object> insertUserCategory(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        int result = userCategoryMapper.insertUserCategory(params);
        if(result >= 1) {
            resultParams.put("resultMsg", "Success");
        }  else {
            resultParams.put("resultMsg", "Fail");
        }
        return resultParams;
    }

    public Map<String, Object> updateUserCategory(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        userCategoryMapper.deleteAllUserCategory(params);

        int result = userCategoryMapper.insertUserCategory(params);
        if(result >= 1) {
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