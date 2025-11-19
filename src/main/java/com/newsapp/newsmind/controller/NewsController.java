package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.dto.NewsItemDto;
import com.newsapp.newsmind.service.NewsService;
import com.newsapp.newsmind.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
@Validated
public class NewsController {

    private final NewsService newsService;

    private final TranslationService translationService;

    // ✅ 국가 + 카테고리 + 키워드 (3개 중 일부만 선택 가능)
    @PostMapping("/combined")
    public List<NewsItemDto> getCombined(@RequestBody Map<String, Object> body) {
        String country = (String) body.get("country");
        String category = (String) body.get("category");
        String keyword = (String) body.get("keyword");
        String translate = (String) body.get("translate");
        String target = (String) body.get("target");
        int limit = body.get("limit") != null ? ((Number) body.get("limit")).intValue() : 10;

        List<NewsItemDto> items = newsService.getCombined(country, category, keyword, limit);
        if ("Y".equals(translate)) {
            items = translationService.attachTranslatedFields(items, target == null ? "ko" : target);
        }
        return items;
    }

    // ✅ 특정 국가만
    @PostMapping("/by-country")
    public List<NewsItemDto> getByCountry(@RequestBody Map<String, Object> body) {
        String country = (String) body.get("country");
        String translate = (String) body.get("translate");
        String target = (String) body.get("target");
        int limit = body.get("limit") != null ? ((Number) body.get("limit")).intValue() : 10;

        List<NewsItemDto> items = newsService.getByCountry(country, limit);
        if ("Y".equals(translate)) {
            items = translationService.attachTranslatedFields(items, target == null ? "ko" : target);
        }
        return items;
    }

    // ✅ 특정 카테고리만
    @PostMapping("/by-category")
    public List<NewsItemDto> getByCategory(@RequestBody Map<String, Object> body) {
        String category = (String) body.get("category");
        String translate = (String) body.get("translate");
        String target = (String) body.get("target");
        int limit = body.get("limit") != null ? ((Number) body.get("limit")).intValue() : 10;

        List<NewsItemDto> items = newsService.getByCategory(category, limit);
        if ("Y".equals(translate)) {
            items = translationService.attachTranslatedFields(items, target == null ? "ko" : target);
        }
        return items;
    }

    // ✅ 특정 키워드만
    @PostMapping("/by-keyword")
    public List<NewsItemDto> getByKeyword(@RequestBody Map<String, Object> body) {
        String keyword = (String) body.get("keyword");
        String translate = (String) body.get("translate");
        String target = (String) body.get("target");
        int limit = body.get("limit") != null ? ((Number) body.get("limit")).intValue() : 10;

        List<NewsItemDto> items = newsService.getByKeyword(keyword, limit);
        if ("Y".equals(translate)) {
            items = translationService.attachTranslatedFields(items, target == null ? "ko" : target);
        }
        return items;
    }
}
