package com.newsapp.newsmind.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class NewsDataArticleDto {
    @JsonProperty("article_id")
    private String articleId;

    private String link;
    private String title;
    private String description;
    private String content;

    @JsonProperty("pubDate")
    private String pubDate;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("source_id")
    private String sourceId;

    @JsonProperty("source_name")
    private String sourceName;

    @JsonProperty("ai_tag")
    private String aiTag;

    private Boolean duplicate;

    private List<String> keywords;
    private List<String> creator;
    private List<String> country;
    private List<String> category;

    // 번역 필드
    private String titleTranslated;
    private String descriptionTranslated;
    private String contentTranslated;
    private String translatedLang;
}
