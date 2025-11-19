package com.newsapp.newsmind.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class AzureTranslatorClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${azure.translator.key}")
    private String key;

    @Value("${azure.translator.region}")
    private String region;

    @Value("${azure.translator.endpoint}")
    private String endpoint;

    public String translate(String text, String targetLang) {
        if (text == null || text.isBlank()) return text;

        String url = endpoint + "/translate?api-version=3.0&to=" + targetLang;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", key);
        headers.set("Ocp-Apim-Subscription-Region", region);

        List<Map<String, String>> body = List.of(Map.of("Text", text));
        HttpEntity<List<Map<String, String>>> entity = new HttpEntity<>(body, headers);

        try {
            List<Map<String, Object>> response = restTemplate.postForObject(url, entity, List.class);
            if (response != null && !response.isEmpty()) {
                Map<String, Object> first = response.get(0);
                List<Map<String, Object>> translations = (List<Map<String, Object>>) first.get("translations");
                if (translations != null && !translations.isEmpty()) {
                    return (String) translations.get(0).get("text");
                }
            }
        } catch (Exception e) {
            System.err.println("Azure Translation API Error: " + e.getMessage());
        }

        return text; // 실패 시 원문 반환
    }
}
