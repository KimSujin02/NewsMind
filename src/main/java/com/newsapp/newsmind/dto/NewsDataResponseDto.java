package com.newsapp.newsmind.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NewsDataResponseDto {
    private String status;

    @JsonProperty("totalResults")
    private Integer totalResults;

    private List<NewsDataArticleDto> results;

    @JsonProperty("nextPage")
    private String nextPage;
}
