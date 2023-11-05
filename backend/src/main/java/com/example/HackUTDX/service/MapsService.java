package com.example.HackUTDX.service;

import com.example.HackUTDX.dao.MapsDao;
import com.example.HackUTDX.models.GeocodedLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MapsService {
    @Autowired
    MapsDao mapsDao;

    @Autowired
    PalmService palmService;


    public List<GeocodedLocation> findFilteredPOIs(String src, String dst, String pref) {
        GeocodedLocation geocodedSrc = mapsDao.geocode(src);
        GeocodedLocation geocodedDst = mapsDao.geocode(dst);
        List<Map<String, String>> response = palmService.genPOIs(src, dst, pref);
        List<GeocodedLocation> geocodedLocations = response.stream().map(poi -> {
            GeocodedLocation loc = mapsDao.geocode(String.format("%s %s, %s",
                    poi.get("name"), poi.get("city"), poi.get("state")));
            loc.setName(poi.get("name"));
            return loc;
        }).collect(Collectors.toList());
        return filterPOIs(geocodedLocations, geocodedSrc, geocodedDst);
    }

    /**
     * filters locs by the most specific geocoded region shared by loc1 and loc2
     */
    private List<GeocodedLocation> filterPOIs(List<GeocodedLocation> locs, GeocodedLocation loc1, GeocodedLocation loc2) {
        String specificMatch = "";
        for (GeocodedLocation.AddressComponent component1 : loc1.getAddress_components()) {
            if (!specificMatch.isEmpty()) {
                break;
            }
            for (GeocodedLocation.AddressComponent component2 : loc2.getAddress_components()) {
                if (component1.getLong_name().equals(component2.getLong_name())) {
                    specificMatch = component1.getShort_name();
                    break;
                }
            }
        }
        String finalSpecificMatch = specificMatch;
        return locs.stream().filter(loc -> {
            if (loc == null) {
                return false;
            }
            if (finalSpecificMatch.isEmpty()) {
                return true;
            }
            for (GeocodedLocation.AddressComponent comp : loc.getAddress_components()) {
                if (comp.getShort_name().equals(finalSpecificMatch)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
    }
}
