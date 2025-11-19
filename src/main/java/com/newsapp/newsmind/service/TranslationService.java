package com.newsapp.newsmind.service;

import com.newsapp.newsmind.dto.NewsItemDto;
import com.newsapp.newsmind.client.AzureTranslatorClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslationService {

    private final AzureTranslatorClient translator;

    public List<NewsItemDto> attachTranslatedFields(List<NewsItemDto> items, String targetLang) {
        return items.stream()
                .map(i -> i.toBuilder()
                        .titleTranslated(translator.translate(i.getTitle(), targetLang))
                        .descriptionTranslated(translator.translate(i.getDescription(), targetLang))
                        .translatedLang(targetLang)
                        .build())
                .toList();
    }
}