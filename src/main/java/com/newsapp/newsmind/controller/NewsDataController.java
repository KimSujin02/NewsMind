package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.dto.NewsDataArticleDto;
import com.newsapp.newsmind.dto.NewsDataRequestDto;
import com.newsapp.newsmind.dto.NewsDataResponseDto;
import com.newsapp.newsmind.service.NewsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/newsdata")
@RequiredArgsConstructor
public class NewsDataController {

    private final NewsDataService newsDataService;

    // 추천뉴스
    @PostMapping("/getTotalNewsList")
    public NewsDataResponseDto getTotalNewsList(@RequestBody Map<String, Object> params) {
        return newsDataService.getTotalNewsList(params);
    }

    // 통합
    @PostMapping("/filterSearch")
    public NewsDataResponseDto filterSearch(@RequestBody NewsDataRequestDto req) {
        return newsDataService.filterSearch(req);
    }

    // 최신 트렌드 10개만 가능
    @PostMapping("/latest")
    public NewsDataResponseDto latest(@RequestBody NewsDataRequestDto req) {
        return newsDataService.latest(req);
    }

    // 언론사 검색
    @PostMapping("/sources")
    public NewsDataResponseDto sources(@RequestBody NewsDataRequestDto req) {
        return newsDataService.sources(req);
    }

    // crypto 검색
    @PostMapping("/crypto")
    public NewsDataResponseDto crypto(@RequestBody NewsDataRequestDto req) {
        return newsDataService.crypto(req);
    }

    // market 검색
    @PostMapping("/market")
    public NewsDataResponseDto market(@RequestBody NewsDataRequestDto req) {
        return newsDataService.market(req);
    }

    // 아티클 id로 상세 내용 출력
    @PostMapping("/detail")
    public NewsDataArticleDto detail(@RequestBody NewsDataRequestDto req) {
        return newsDataService.detail(req);
    }
}