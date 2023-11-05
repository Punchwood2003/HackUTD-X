package com.example.HackUTDX.controller;

import com.example.HackUTDX.dao.MapsDao;

import com.example.HackUTDX.models.Directions;
import com.example.HackUTDX.service.PalmService;

import com.example.HackUTDX.models.GeocodedLocation;
import com.example.HackUTDX.service.MapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class Controller {
    @Autowired
    MapsDao mapsDao;
    @Autowired
    MapsService mapsService;
    @Autowired
    PalmService palmService;

    @GetMapping("directions")
    public Directions getDirections(@RequestParam String src, @RequestParam String dst) {
        return mapsDao.getDirections(src, dst, null);
    }

    @GetMapping("find-pois")
    public List<GeocodedLocation> findPois(@RequestParam String src, @RequestParam String dst, @RequestParam String place) {
        return mapsService.findFilteredPOIs(src, dst, place);
    }
}