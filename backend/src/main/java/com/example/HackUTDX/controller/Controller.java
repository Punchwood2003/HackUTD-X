package com.example.HackUTDX.controller;

import com.example.HackUTDX.dao.MapsDao;
import com.example.HackUTDX.dao.PalmDao;

import com.example.HackUTDX.service.PalmService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class Controller {
    @Autowired
    MapsDao mapsDao;

    @Autowired
    PalmDao palmDao;

    @Autowired
    PalmService palmService;

    @GetMapping("directions")
    public String getDirections(@RequestParam String src, @RequestParam String dst) {
        return mapsDao.getDirections(src, dst, null);
    }

//    @GetMapping("palm")
//    public String prompt(@RequestParam String prompt) {
//        return palmDao.getResponse(prompt);
//    }

    @GetMapping("test")
    public String test(@RequestParam String src, String dest, String place){
        return palmService.generateText(src, dest, place);
    }
}
