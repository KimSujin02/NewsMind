package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.service.UserKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/Keyword")
@RequiredArgsConstructor
public class UserKeywordController {

    private final UserKeywordService userKeywordService;

    @PostMapping("/insertUserKeyword")
    public Map<String, Object> insertUserKeyword(@RequestBody Map<String, Object> params) {
        return userKeywordService.insertUserKeyword(params);
    }

    @DeleteMapping("/deleteUserKeyword")
    public Map<String, Object> deleteUserKeyword(@RequestBody Map<String, Object> params) {
        return userKeywordService.deleteUserKeyword(params);
    }

    @PostMapping("/selectUserKeywordList")
    public List<Map<String, Object>> selectUserKeywordList(@RequestBody Map<String, Object> params) {
        return userKeywordService.selectUserKeywordList(params);
    }
}
