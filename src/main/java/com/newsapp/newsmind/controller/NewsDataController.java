package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.dto.NewsDataArticleDto;
import com.newsapp.newsmind.dto.NewsDataRequestDto;
import com.newsapp.newsmind.dto.NewsDataResponseDto;
import com.newsapp.newsmind.service.NewsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/newsdata")
@RequiredArgsConstructor
public class NewsDataController {

    private final NewsDataService service;

    // 통합
    @PostMapping("/filterSearch")
    public NewsDataResponseDto filterSearch(@RequestBody NewsDataRequestDto req) {
        return service.filterSearch(req);
    }

    // 최신 트렌드 10개만 가능
    @PostMapping("/latest")
    public NewsDataResponseDto latest(@RequestBody NewsDataRequestDto req) {
        return service.latest(req);
    }

    // 언론사 검색
    @PostMapping("/sources")
    public NewsDataResponseDto sources(@RequestBody NewsDataRequestDto req) {
        return service.sources(req);
    }

    // crypto 검색
    @PostMapping("/crypto")
    public NewsDataResponseDto crypto(@RequestBody NewsDataRequestDto req) {
        return service.crypto(req);
    }

    // market 검색
    @PostMapping("/market")
    public NewsDataResponseDto market(@RequestBody NewsDataRequestDto req) {
        return service.market(req);
    }

    // 아티클 id로 상세 내용 출력
    @PostMapping("/detail")
    public NewsDataArticleDto detail(@RequestBody NewsDataRequestDto req) {
        return service.detail(req);
    }
}