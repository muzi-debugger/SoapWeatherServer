package com.muzi.soapserver.service;


import com.muzi.soapserver.model.GetWeatherRequest;
import com.muzi.soapserver.model.GetWeatherResponse;

public interface WeatherServiceInterface {
    GetWeatherResponse getWeather(GetWeatherRequest request);
}
