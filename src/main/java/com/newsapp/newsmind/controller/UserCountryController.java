package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.service.UserCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/country")
@RequiredArgsConstructor
public class UserCountryController {

    private final UserCountryService userCountryService;

    @PostMapping("/insertUserCountry")
    public Map<String, Object> insertUserCountry(@RequestBody Map<String, Object> params) {
        return userCountryService.insertUserCountry(params);
    }

    @PostMapping("/updatePrimaryUserCountry")
    public Map<String, Object> updatePrimaryUserCountry(@RequestBody Map<String, Object> params) {
        return userCountryService.updatePrimaryUserCountry(params);
    }

    @DeleteMapping("/deleteUserCountry")
    public Map<String, Object> deleteUserCountry(@RequestBody Map<String, Object> params) {
        return userCountryService.deleteUserCountry(params);
    }

    @DeleteMapping("/deleteAllUserCountry")
    public Map<String, Object> deleteAllUserCountry(@RequestBody Map<String, Object> params) {
        return userCountryService.deleteAllUserCountry(params);
    }

    @PostMapping("/selectUserCountryList")
    public List<Map<String, Object>> selectUserCountryList(@RequestBody Map<String, Object> params) {
        return userCountryService.selectUserCountryList(params);
    }
}
