package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.service.UserSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/Source")
@RequiredArgsConstructor
public class UserSourceController {

    private final UserSourceService userSourceService;

    @PostMapping("/insertUserSource")
    public Map<String, Object> insertUserSource(@RequestBody Map<String, Object> params) {
        return userSourceService.insertUserSource(params);
    }

    @DeleteMapping("/deleteUserSource")
    public Map<String, Object> deleteUserSource(@RequestBody Map<String, Object> params) {
        return userSourceService.deleteUserSource(params);
    }

    @PostMapping("/selectUserSourceList")
    public List<Map<String, Object>> selectUserSourceList(@RequestBody Map<String, Object> params) {
        return userSourceService.selectUserSourceList(params);
    }
}
