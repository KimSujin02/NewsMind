package com.newsapp.newsmind.client;

import com.newsapp.newsmind.dto.NewsApiResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NewsApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${app.search.newsapi.api-key}")
    private String apiKey;

    @Value("${app.search.newsapi.page-size:10}")
    private int defaultPageSize;

    public NewsApiResponseDto getTopHeadlines(String country, String category, String keyword, int pageSize) {
        String url = "https://newsapi.org/v2/top-headlines?pageSize=" + pageSize;

        if (country != null && !country.isBlank()) url += "&country=" + country;
        if (category != null && !category.isBlank()) url += "&category=" + category;
        if (keyword != null && !keyword.isBlank()) url += "&q=" + keyword;

        return restTemplate
                .getForEntity(url + "&apiKey=" + apiKey, NewsApiResponseDto.class)
                .getBody();
    }
}
