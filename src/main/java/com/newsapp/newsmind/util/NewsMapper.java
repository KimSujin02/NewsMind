package com.newsapp.newsmind.util;

import com.newsapp.newsmind.dto.NewsItemDto;

import java.time.OffsetDateTime;
import java.util.Map;

@SuppressWarnings("unchecked")
public class NewsMapper {

    public static NewsItemDto toItemDto(Map<String, Object> raw) {
        Map<String, Object> source = (Map<String, Object>) raw.getOrDefault("source", Map.of());
        return NewsItemDto.builder()
                .title((String) raw.getOrDefault("title", ""))
                .description((String) raw.getOrDefault("description", ""))
                .url((String) raw.getOrDefault("url", ""))
                .urlToImage((String) raw.getOrDefault("urlToImage", ""))
                .sourceName((String) source.getOrDefault("name", ""))
                .publishedAt(parseDate((String) raw.get("publishedAt")))
                .build();
    }

    private static OffsetDateTime parseDate(String s) {
        try { return s == null ? null : OffsetDateTime.parse(s); }
        catch (Exception e) { return null; }
    }
}
