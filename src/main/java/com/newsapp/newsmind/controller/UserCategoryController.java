package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.service.UserCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/category")
@RequiredArgsConstructor
public class UserCategoryController {

    private final UserCategoryService userCategoryService;

    @PostMapping("/insertUserCategory")
    public Map<String, Object> insertUserCategory(@RequestBody Map<String, Object> params) {
        return userCategoryService.insertUserCategory(params);
    }

    @DeleteMapping("/updateUserCategory")
    public Map<String, Object> updateUserCategory(@RequestBody Map<String, Object> params) {
        return userCategoryService.updateUserCategory(params);
    }

    @PostMapping("/selectUserCategoryList")
    public List<Map<String, Object>> selectUserCategoryList(@RequestBody Map<String, Object> params) {
        return userCategoryService.selectUserCategoryList(params);
    }
}
