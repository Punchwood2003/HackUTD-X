package com.example.HackUTDX.controller;

import com.example.HackUTDX.dao.MapsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("api")
public class Controller {
    @Autowired
    MapsDao mapsDao;
    @GetMapping("/directions")
    public String getDirections(@RequestParam String src, @RequestParam String dst) {
        return mapsDao.getDirections(src, dst, null);
    }
}
