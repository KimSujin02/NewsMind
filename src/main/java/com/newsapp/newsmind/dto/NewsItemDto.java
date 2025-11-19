package com.newsapp.newsmind.dto;

import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class NewsItemDto {
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String sourceName;
    private OffsetDateTime publishedAt;

    private String titleTranslated;       // 번역된 제목
    private String descriptionTranslated; // 번역된 요약
    private String translatedLang;        // 번역 대상 언어 코드 (예: "ko")
}