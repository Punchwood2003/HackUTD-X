package com.example.HackUTDX.config;

import lombok.Getter;
import lombok.Setter;

import java.beans.BeanProperty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
@Setter
@PropertySource("classpath:apikeys.properties")
public class PalmConfig {
    @Value("${palm.palmURL}")
    private String palmURL;
    @Value("${apikeys.palm}")
    private String api_key;
}
