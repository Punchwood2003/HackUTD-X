package com.example.HackUTDX.service;

import java.net.http.HttpHeaders;

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

    public String generateText(String src, String dest, String place) {
        return palmDao.getResponse(src, dest, place);
    }
}

