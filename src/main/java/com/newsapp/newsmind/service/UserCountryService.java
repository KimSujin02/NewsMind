package com.newsapp.newsmind.service;

import com.newsapp.newsmind.dto.UserDto;
import com.newsapp.newsmind.mapper.UserCountryMapper;
import com.newsapp.newsmind.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCountryService {

    private final UserCountryMapper userCountryMapper;
    private final UserMapper userMapper;

    public Map<String, Object> updateUserCountry(Map<String, Object> params) {
        Map<String, Object> resultParams = new HashMap<>();

        userCountryMapper.deleteAllUserCountry(params);

        List<String> countries = ((List<?>) params.get("countries"))
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toList());

        int priorityOrd = 1;
        int result = 0;
        for (String country : countries) {
            params.put("countryCode", country);
            params.put("priorityOrd", priorityOrd++);
            result += userCountryMapper.insertUserCountry(params);
        }

        if(result == countries.size()) {
            UserDto user = userMapper.findByUserId(Long.valueOf(params.get("userId").toString()));
            if (user.getSettingCompleted() == false) {
                userMapper.updateSettingCompleted(user.getUserId());
            }
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

    public List<Map<String, Object>> selectUserCountryList(Map<String, Object> params) {
        return userCountryMapper.selectUserCountryList(params);
    }
}