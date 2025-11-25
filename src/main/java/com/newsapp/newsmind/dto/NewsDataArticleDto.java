package com.newsapp.newsmind.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class NewsDataArticleDto {
    private String articleId;
    private String title;
    private String description;
    private String content;
    private String link;
    private String imageUrl;
    private String pubDate;
    private String sourceId;

    // 번역 필드
    private String titleTranslated;
    private String descriptionTranslated;
    private String contentTranslated;
    private String translatedLang;
}
