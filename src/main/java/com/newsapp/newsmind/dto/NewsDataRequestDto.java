package com.newsapp.newsmind.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewsDataRequestDto {
    private List<String> countries;
    private List<String> categories;
    private List<String> keywords;
    private List<String> domains;

    private String country;
    private String category;
    private String keyword;
    private String domain;

    private String endPoint;

    // nextPage 방식 페이징, 다음 페이지나 이전 페이지로 갈때 사용
    private String page;

    private String fromDate;
    private String toDate;

    // 번역 옵션
    private boolean translate;
    private String targetLang;

    // detail 전용
    private String articleId;
}