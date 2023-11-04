package com.example.HackUTDX.dao;


import com.example.HackUTDX.config.MapsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MapsDao {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MapsConfig mapsConfig;

    public String getDirections(String src, String dst, List<String> waypoints) {
        Map<String, String> params = new HashMap<>();
        params.put("origin", src);
        params.put("destination", dst);
        if (waypoints != null) {
            params.put("waypoints", formatWaypoints(waypoints));
        }
        return restTemplate.getForObject(mapsConfig.getDirectionsURL() + formatParams(params), String.class);
    }

    private String formatWaypoints(List<String> waypoints) {
        return String.join("||", waypoints);
    }

    private String formatParams(Map<String, String> paramMap) {
        return String.join("&", paramMap.entrySet()
                .stream().map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.toList()));
    }
}
