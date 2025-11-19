package com.newsapp.newsmind.service;

import com.newsapp.newsmind.client.NewsApiClient;
import com.newsapp.newsmind.dto.NewsApiResponseDto;
import com.newsapp.newsmind.dto.NewsItemDto;
import com.newsapp.newsmind.util.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsApiClient client;

    /**
     * 국가 + 카테고리 + 키워드 조합으로 뉴스 가져오기
     */
    public List<NewsItemDto> getCombined(String country, String category, String keyword, int limit) {
        // 3개 모두 비어있으면 NewsAPI에서 에러 발생 → 미리 방어
        if ((country == null || country.isBlank()) &&
                (category == null || category.isBlank()) &&
                (keyword == null || keyword.isBlank())) {
            throw new IllegalArgumentException("country, category, keyword 중 하나 이상은 필수입니다.");
        }

        NewsApiResponseDto response = client.getTopHeadlines(country, category, keyword, limit);
        return mapAndSort(response, limit);
    }

    /**
     * 특정 국가 뉴스
     */
    public List<NewsItemDto> getByCountry(String country, int limit) {
        return getCombined(country, null, null, limit);
    }

    /**
     * 특정 카테고리 뉴스
     */
    public List<NewsItemDto> getByCategory(String category, int limit) {
        return getCombined(null, category, null, limit);
    }

    /**
     * 특정 키워드 뉴스
     */
    public List<NewsItemDto> getByKeyword(String keyword, int limit) {
        return getCombined(null, null, keyword, limit);
    }

    // ------------------ 내부 유틸 ------------------

    private List<NewsItemDto> mapAndSort(NewsApiResponseDto response, int limit) {
        if (response == null || response.getArticles() == null) return List.of();

        return response.getArticles().stream()
                .map(NewsMapper::toItemDto)
                .filter(i -> i.getUrl() != null && !i.getUrl().isBlank())
                .collect(Collectors.toMap(NewsItemDto::getUrl, i -> i, (a, b) -> a)) // 중복 제거
                .values().stream()
                .sorted(Comparator.comparing(NewsItemDto::getPublishedAt,
                        Comparator.nullsLast(Comparator.reverseOrder())))
                .limit(limit)
                .toList();
    }
}
