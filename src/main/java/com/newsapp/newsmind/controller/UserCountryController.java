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

    @PostMapping("/updateUserCountry")
    public Map<String, Object> updateUserCountry(@RequestBody Map<String, Object> params) {
        return userCountryService.updateUserCountry(params);
    }

    @PostMapping("/updatePrimaryUserCountry")
    public Map<String, Object> updatePrimaryUserCountry(@RequestBody Map<String, Object> params) {
        return userCountryService.updatePrimaryUserCountry(params);
    }

    @PostMapping("/selectUserCountryList")
    public List<Map<String, Object>> selectUserCountryList(@RequestBody Map<String, Object> params) {
        return userCountryService.selectUserCountryList(params);
    }
}
