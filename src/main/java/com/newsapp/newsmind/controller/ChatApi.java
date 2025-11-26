package com.newsapp.newsmind.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import org.springframework.ai.chat.client.ChatClient;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.ai.enabled", havingValue = "true")
public class ChatApi {

    private final ChatClient.Builder chatClientBuilder;

    @GetMapping
    public String ask(@RequestParam String q) {
        return chatClientBuilder.build()
                .prompt().user(q).call().content();
    }
}