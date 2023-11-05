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

    public String getResponse(String src, String dest, String place) {
        String prompt = "Suppose you are planning a route from " + src + " to " + dest +
                ". Give me a list of cool " + place + "s on this route." +
                "Return these" + place + "s as Tuples where each Tuple is formatted as such: " +
                "(name, City, State)" + "." + "Make sure that each " + place +
                " gets significantly closer towards " + dest +
                ", and is spaced out from the last " + place + " before it and on the way towards " +
                place + ". Each tuple should have three attributes: " +
                "One for the name of the " + place + "one for the city, and one for the state." +
                "At most, return 10 tuples. Send these tuples as plain text seperated by commas. " +
                "Do not use markdown or any special characters or escape sequences. Do not seperate the tuples with new lines";
        Map<String, String> params = new HashMap<>();
        params.put("key", palmConfig.getApi_key());
        String url = palmConfig.getPalmURL() + "?" + formatParams(params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> promptData = new HashMap<>();
        String temperature = "0.2";
        promptData.put("text", prompt);
        requestBody.put("prompt", promptData);
        requestBody.put("temperature", temperature);

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
