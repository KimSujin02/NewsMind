package com.newsapp.newsmind.dto;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsApiResponseDto {
    private String status;
    private int totalResults;
    private List<Map<String, Object>> articles;
}
