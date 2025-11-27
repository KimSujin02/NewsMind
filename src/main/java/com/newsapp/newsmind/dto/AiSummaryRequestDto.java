package com.newsapp.newsmind.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AiSummaryRequestDto {
    private String articleId;
    private String content;
}