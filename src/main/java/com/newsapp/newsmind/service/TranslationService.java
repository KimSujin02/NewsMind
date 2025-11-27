package com.newsapp.newsmind.service;

import com.newsapp.newsmind.dto.NewsDataArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TranslationService {

    @Value("${azure.translator.key}")
    private String key;

    @Value("${azure.translator.region}")
    private String region;

    @Value("${azure.translator.endpoint}")
    private String endpoint;

    private final RestTemplate restTemplate = new RestTemplate();

    public String translate(String text, String targetLang) {
        if (text == null || text.isBlank()) return text;

        String url = endpoint + "/translate?api-version=3.0&to=" + targetLang;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", key);
        headers.set("Ocp-Apim-Subscription-Region", region);

        List<Map<String, String>> body = List.of(Map.of("Text", text));
        HttpEntity<List<Map<String, String>>> req = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<List> res = restTemplate.exchange(url, HttpMethod.POST, req, List.class);
            Map<String, Object> first = (Map<String, Object>) res.getBody().get(0);
            List<Map<String, Object>> translations = (List<Map<String, Object>>) first.get("translations");
            return (String) translations.get(0).get("text");
        } catch (Exception e) {
            return text;
        }
    }

    // 리스트 번역
    public List<NewsDataArticleDto> translateList(List<NewsDataArticleDto> list, String targetLang) {
        return list.stream().map(i -> translateOne(i, targetLang)).toList();
    }

    // 하나 번역
    public NewsDataArticleDto translateOne(NewsDataArticleDto item, String targetLang) {
//        return item.toBuilder()
//                .titleTranslated(translate(item.getTitle(), targetLang))
//                .descriptionTranslated(translate(item.getDescription(), targetLang))
//                .contentTranslated(translate(item.getContent(), targetLang))
//                .translatedLang(targetLang)
//                .build();
        return item.toBuilder()
                .title(translate(item.getTitle(), targetLang))
                .description(translate(item.getDescription(), targetLang))
                .content(translate(item.getContent(), targetLang))
                .translatedLang(targetLang)
                .build();
    }
}