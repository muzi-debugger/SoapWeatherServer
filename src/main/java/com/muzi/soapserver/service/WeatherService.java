package com.muzi.soapserver.service;

// WeatherService.java - This class will handle the SOAP web service endpoint

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.muzi.soapserver.config.WeatherApiProperties;
import com.muzi.soapserver.model.WeatherData;


@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final WeatherApiProperties apiProperties;

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    public WeatherService(WeatherApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }


    public WeatherData fetchWeather(String city, String country) {
        WeatherData data = new WeatherData();

        String apiKey = apiProperties.getApiKey();

        try {
            String url = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s,%s&units=metric&appid=%s",
                city, country, apiKey
            );

            log.info("Fetching weather for {}, {} with api key {}", city, country, apiKey);

            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            Map<String, Object> body = response.getBody();
            if (body != null) {
                Map<String, Object> main = (Map<String, Object>) body.get("main");
                List<Map<String, Object>> weatherList = (List<Map<String, Object>>) body.get("weather");

                data.setTemperature((Double) main.get("temp"));
                data.setDescription((String) weatherList.get(0).get("description"));
            }

        } catch (RestClientException e) {
            data.setDescription("Unavailable");
            data.setTemperature(0.0);
            data.setErrorMessage("Unable to fetch weather for " + city);
        }

        return data;
    }

    
}
