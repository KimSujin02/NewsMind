package com.newsapp.newsmind.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewsDataResponseDto {
    private String status;
    private List<NewsDataArticleDto> results;
    private String nextPage;
}
