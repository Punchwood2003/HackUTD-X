package com.example.HackUTDX.dao;

import com.example.HackUTDX.config.MapsConfig;
import com.example.HackUTDX.config.PalmConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PalmDao {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    PalmConfig palmConfig;

    public String getResponse(String prompt) {
        Map<String, String> params = new HashMap<>();
        params.put("key", palmConfig.getApi_key());
        String url = palmConfig.getPalmURL() + "?" + formatParams(params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> promptData = new HashMap<>();
        promptData.put("text", prompt);
        requestBody.put("prompt", promptData);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        return responseEntity.getBody();
    }

    private String formatParams(Map<String, String> paramMap) {
        return String.join("&", paramMap.entrySet()
                .stream().map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.toList()));
    }

}
