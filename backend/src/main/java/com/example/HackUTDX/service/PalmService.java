package com.example.HackUTDX.service;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.HackUTDX.models.PalmResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.HackUTDX.dao.PalmDao;

@Service
public class PalmService {

    @Autowired PalmDao palmDao;
    public List<Map<String, String>> genPOIs(String src, String dest, String perf) {
        PalmResponseModel response = palmDao.getResponse(src, dest, perf);
        return formatResponse(response);
    }

    private static List<Map<String, String>> formatResponse(PalmResponseModel response) {
        String responseText = response.getCandidates().get(0).getOutput();
        responseText = responseText.replaceAll("[()']", "");
        responseText = responseText.substring(responseText.indexOf('\n') + 1);

        List<Map<String, String>> responseList = new ArrayList<>();
        String[] entries = responseText.split(",\n");

        for (String entry : entries) {
            String[] parts = entry.split(", ");
            if (parts.length == 3) {
                Map<String, String> coffeeShop = new HashMap<>();
                coffeeShop.put("name", parts[0].trim());
                coffeeShop.put("city", parts[1].trim());
                coffeeShop.put("state", parts[2].trim());
                responseList.add(coffeeShop);
            }
        }
        return responseList;
    }
}

