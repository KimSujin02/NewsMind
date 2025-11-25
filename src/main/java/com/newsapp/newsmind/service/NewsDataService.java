package com.newsapp.newsmind.service;

import com.newsapp.newsmind.client.NewsDataClient;
import com.newsapp.newsmind.dto.NewsDataArticleDto;
import com.newsapp.newsmind.dto.NewsDataRequestDto;
import com.newsapp.newsmind.dto.NewsDataResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsDataService {

    private final NewsDataClient client;
    private final TranslationService translator;

    private String joinComma(List<String> list) {
        return (list == null || list.isEmpty()) ? null : String.join(",", list);
    }
    private String joinSpace(List<String> list) {
        return (list == null || list.isEmpty()) ? null : String.join(" ", list);
    }

    // 기사 검색 /news /archive /latest
    public NewsDataResponseDto filterSearch(NewsDataRequestDto req) {
        req.setCountry(joinComma(req.getCountries()));
        req.setCategory(joinComma(req.getCategories()));
        req.setKeyword(joinSpace(req.getKeywords()));
        req.setDomain(joinComma(req.getDomains()));

        NewsDataResponseDto resp = client.filterSearch(req);

        if (req.isTranslate()) {
            resp.setResults(
                    translator.translateList(resp.getResults(), req.getTargetLang())
            );
        }
        return resp;
    }

    // Latest 최신
    public NewsDataResponseDto latest(NewsDataRequestDto req) {
        req.setEndPoint("latest");
        return filterSearch(req);
    }

    // 언론사 검색
    public NewsDataResponseDto sources(NewsDataRequestDto req) {
        req.setEndPoint("sources");
        return filterSearch(req);
    }

    // crypto 검색
    public NewsDataResponseDto crypto(NewsDataRequestDto req) {
        req.setEndPoint("crypto");
        return filterSearch(req);
    }

    // market 검색
    public NewsDataResponseDto market(NewsDataRequestDto req) {
        req.setEndPoint("market");
        return filterSearch(req);
    }

    // Detail(articleId)
    public NewsDataArticleDto detail(NewsDataRequestDto req) {

        NewsDataResponseDto resp = client.detail(req.getArticleId());
        if (resp.getResults() == null || resp.getResults().isEmpty()) return null;

        NewsDataArticleDto item = resp.getResults().get(0);

        return req.isTranslate()
                ? translator.translateOne(item, req.getTargetLang())
                : item;
    }
}
