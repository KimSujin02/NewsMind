package com.newsapp.newsmind.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AiSummaryResponseDto {
    private String summary;
    private String[] analysis;
    private String[] keywords;
}