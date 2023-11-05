package com.example.HackUTDX.dao;


import com.example.HackUTDX.config.MapsConfig;
import com.example.HackUTDX.models.Directions;
import com.example.HackUTDX.models.GeocodedLocation;
import com.example.HackUTDX.models.GeocodedLocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    public Directions getDirections(String src, String dst, List<String> waypoints) {
        Map<String, String> params = new HashMap<>();
        params.put("origin", src);
        params.put("destination", dst);
        params.put("key",mapsConfig.getApi_key());
        if (waypoints != null) {
            params.put("waypoints", formatWaypoints(waypoints));
        }
        String url = mapsConfig.getDirectionsURL() + "?" + formatParams(params);
        Directions response = restTemplate.getForObject(url, Directions.class);
        return response;
    }

    @Cacheable("geocodedLoc")
    public GeocodedLocation geocode(String loc) {
        Map<String, String> params = new HashMap<>();
        params.put("key", mapsConfig.getApi_key());
        params.put("address", loc);
        String url = mapsConfig.getGeocodeURL() + "?" + formatParams(params);
        GeocodedLocationResponse response = restTemplate.getForObject(url, GeocodedLocationResponse.class);
        if (response.getResults().isEmpty()) {
            response = restTemplate.getForObject(url, GeocodedLocationResponse.class);
            if (response.getResults().isEmpty()) {
                return null;
            }
        }
        return response.getResults().get(0);
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
