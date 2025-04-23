package com.muzi.soapserver.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;

import com.muzi.soapserver.exception.WeatherServiceException;
import com.muzi.soapserver.model.GetWeatherRequest;
import com.muzi.soapserver.model.GetWeatherResponse;
import com.muzi.soapserver.model.ObjectFactory;
import com.muzi.soapserver.model.WeatherData;
import com.muzi.soapserver.service.WeatherService;

import jakarta.xml.bind.JAXBElement;

@Endpoint
public class WeatherEndpoint {
    private static final String NAMESPACE = "http://muzi.com/weather";

    private final WeatherService weatherService;

    public WeatherEndpoint(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getWeatherRequest")
    @ResponsePayload
    public JAXBElement<GetWeatherResponse> handleGetWeather(
            @RequestPayload JAXBElement<GetWeatherRequest> requestElement) {

        GetWeatherRequest req = requestElement.getValue();
        WeatherData data = weatherService.fetchWeather(req.getCity(), req.getCountry());

        if (data.getErrorMessage() != null) {
            throw new WeatherServiceException(data.getErrorMessage());
        }

        GetWeatherResponse resp = new GetWeatherResponse();
        resp.setCity(req.getCity());
        resp.setTemperature(data.getTemperature());
        resp.setDescription(data.getDescription());

        ObjectFactory of = new ObjectFactory();
        return of.createGetWeatherResponse(resp);
    }

    @Bean
    public PayloadLoggingInterceptor payloadLoggingInterceptor() {
        return new PayloadLoggingInterceptor();
    }

    @Bean
    public EndpointInterceptor[] interceptors() {
        return new EndpointInterceptor[] { payloadLoggingInterceptor() };
    }
    

}
