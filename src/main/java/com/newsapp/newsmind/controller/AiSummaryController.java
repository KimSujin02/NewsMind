package com.newsapp.newsmind.controller;

import com.newsapp.newsmind.dto.AiSummaryRequestDto;
import com.newsapp.newsmind.dto.AiSummaryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.ai.enabled", havingValue = "true")
public class AiSummaryController {

    private final ChatClient.Builder chatClientBuilder;

    @PostMapping("/summarize")
    public AiSummaryResponseDto summarize(@RequestBody AiSummaryRequestDto req) {

        String prompt = buildPrompt(req.getContent());

        // Structured Output 방식
        return chatClientBuilder.build()
                .prompt()
                .user(prompt)
                .call()
                .entity(AiSummaryResponseDto.class);
    }

    private String buildPrompt(String content) {
        return """
                당신은 고급 뉴스 분석 엔진입니다.

                아래 기사 내용을 읽고 다음 JSON 형식으로만 출력하세요.
                다른 문장, 설명, 서문, 후기, 코드 블록, 마크다운은 절대 포함하지 마세요.

                {
                  "summary": "3~5문장 한국어 요약",
                  "analysis": ["분석 1", "분석 2"],
                  "keywords": ["키워드1", "키워드2", "키워드3"]
                }

                기사 내용:
                %s
                """
                .formatted(content);
    }
}
