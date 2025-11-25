package com.newsapp.newsmind.service;

import com.newsapp.newsmind.mapper.CodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeMapper codeMapper;

    public List<Map<String, Object>> selectCodeList(Map<String, Object> params) {
        return codeMapper.selectCodeList(params);
    }

}