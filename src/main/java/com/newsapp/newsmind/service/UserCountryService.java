package com.newsapp.newsmind.service;

import com.newsapp.newsmind.mapper.UserCountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserCountryService {

    private final UserCountryMapper userCountryMapper;

    public Map<String, Object> insertUserCountry(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        int result = userCountryMapper.insertUserCountry(params);
        if(result >= 1) {
            resultParams.put("resultMsg", "Success");
        }  else {
            resultParams.put("resultMsg", "Fail");
        }
        return resultParams;
    }

    public Map<String, Object> updatePrimaryUserCountry(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        userCountryMapper.updatePrimaryUserCountryBefore(params);

        int result = userCountryMapper.updatePrimaryUserCountry(params);
        if(result >= 1) {
            resultParams.put("resultMsg", "Success");
        }  else {
            resultParams.put("resultMsg", "Fail");
        }
        return resultParams;
    }

    public Map<String, Object> deleteUserCountry(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        int result = userCountryMapper.deleteUserCountry(params);
        if(result >= 1) {
            resultParams.put("resultMsg", "Success");
        }  else {
            resultParams.put("resultMsg", "Fail");
        }
        return resultParams;
    }

    public Map<String, Object> deleteAllUserCountry(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        int result = userCountryMapper.deleteAllUserCountry(params);
        if(result >= 1) {
            resultParams.put("resultMsg", "Success");
        }  else {
            resultParams.put("resultMsg", "Fail");
        }
        return resultParams;
    }

    public List<Map<String, Object>> selectUserCountryList(Map<String, Object> params) {
        return userCountryMapper.selectUserCountryList(params);
    }
}