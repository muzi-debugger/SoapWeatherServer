package com.muzi.soapserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "spring.weather.api")
public class WeatherApiProperties {

    private static final Logger log = LoggerFactory.getLogger(WeatherApiProperties.class);

    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @PostConstruct
    public void logApiKey() {
        log.info("Weather API Key: {}", apiKey);
    }
}
