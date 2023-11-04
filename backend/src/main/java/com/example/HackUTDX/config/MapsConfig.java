package com.example.HackUTDX.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@Setter
@PropertySource("classpath:apikeys.yaml")
public class MapsConfig {
    @Value("${maps.directionsURL}")
    private String directionsURL;
    @Value("${maps.apiKey}")
    private String api_key;
}
