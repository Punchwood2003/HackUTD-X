package com.example.HackUTDX.controller;

import com.example.HackUTDX.dao.MapsDao;
import com.example.HackUTDX.dao.PalmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class Controller {
    @Autowired
    MapsDao mapsDao;

    @Autowired
    PalmDao palmDao;

    @GetMapping("directions")
    public String getDirections(@RequestParam String src, @RequestParam String dst) {
        return mapsDao.getDirections(src, dst, null);
    }

    @GetMapping("palm")
    public String prompt(@RequestParam String prompt) {
        return palmDao.getResponse(prompt);
    }
}
