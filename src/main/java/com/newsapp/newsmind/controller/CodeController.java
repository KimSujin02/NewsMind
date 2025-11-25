package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @PostMapping("/selectCodeList")
    public List<Map<String, Object>> selectCodeList(@RequestBody Map<String, Object> params) {
        return codeService.selectCodeList(params);
    }
}