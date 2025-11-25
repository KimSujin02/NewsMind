package com.newsapp.newsmind.client;

import com.newsapp.newsmind.dto.NewsDataRequestDto;
import com.newsapp.newsmind.dto.NewsDataResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class NewsDataClient {

    private final RestTemplate rest = new RestTemplate();

    @Value("${app.newsdata.api-key}")
    private String apiKey;

    @Value("${app.newsdata.base-url}")
    private String baseUrl;


    /*
        endpoint 설명
        /latest 기반 타임라인 API 10개 제한
        /news 기반 검색 + 무한스크롤 API
        /archive 기반 과거 뉴스 API
        /crypto 전용 API
        /market 전용 API
    */
    public NewsDataResponseDto filterSearch(NewsDataRequestDto req) {
        String country = req.getCountry();
        String category = req.getCategory();
        String keyword = req.getKeyword();
        String domain = req.getDomain();
        String fromDate = req.getFromDate();
        String toDate = req.getToDate();
        String page = req.getPage();

        String endPoint = "";
        if(req.getEndPoint() != null && !req.getEndPoint().isBlank()) {
            // latest sources crypto market는 endpoint 따로 받음
            endPoint = req.getEndPoint();
        } else if ((fromDate != null && !fromDate.isBlank()) || toDate != null && !toDate.isBlank()) {
            endPoint = "archive";
        } else {
            endPoint = "news";
        }
        
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/" + endPoint)
                .queryParam("apikey", apiKey);

        if (country != null && !country.isBlank()) builder.queryParam("country", country);
        if (category != null && !category.isBlank()) builder.queryParam("category", category);
        if (keyword != null && !keyword.isBlank())  builder.queryParam("q", keyword);
        if (domain != null && !domain.isBlank())  builder.queryParam("domain", domain);
        if (fromDate != null && !fromDate.isBlank()) builder.queryParam("from_date", fromDate);
        if (toDate != null && !toDate.isBlank()) builder.queryParam("to_date", toDate);
        if (page != null && !page.isBlank()) builder.queryParam("page", page);

        return rest.getForObject(builder.toUriString(), NewsDataResponseDto.class);
    }
    
    // 기사 상세 검색만
    public NewsDataResponseDto detail(String articleId) {
        return rest.getForObject(
                baseUrl + "/news?apikey=" + apiKey + "&id=" + articleId,
                NewsDataResponseDto.class
        );
    }
}