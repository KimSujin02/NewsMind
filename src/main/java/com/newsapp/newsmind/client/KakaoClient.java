package com.newsapp.newsmind.client;

import com.newsapp.newsmind.dto.KakaoUserInfoDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.Map;

@Component
public class KakaoClient {
    private final RestTemplate rest = new RestTemplate();

    public String getAccessToken(String code, String clientId, String redirectUri) {
        String url = "https://kauth.kakao.com/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        HttpEntity<?> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> res = rest.exchange(url, HttpMethod.POST, request, Map.class);

        return res.getBody().get("access_token").toString();
    }

    public KakaoUserInfoDto getUserInfo(String accessToken) {
        String url = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<?> req = new HttpEntity<>(headers);

        ResponseEntity<Map> res = rest.exchange(url, HttpMethod.POST, req, Map.class);

        Map body = res.getBody();
        Map account = (Map) body.get("kakao_account");
        Map profile = (Map) account.get("profile");

        KakaoUserInfoDto info = new KakaoUserInfoDto();
        info.setId(body.get("id").toString());
        info.setEmail((String) account.get("email"));
        info.setNickname((String) profile.get("nickname"));

        return info;
    }
}