package com.muzi.soapserver.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.muzi.soapserver.model.GetWeatherRequest;
import com.muzi.soapserver.model.GetWeatherResponse;
import com.muzi.soapserver.model.ObjectFactory;
import com.muzi.soapserver.model.WeatherData;
import com.muzi.soapserver.service.WeatherService;

import jakarta.xml.bind.JAXBElement;

@Endpoint
public class WeatherEndpoint {
    // namespace and localPart must match your XSD
    private static final String NAMESPACE = "http://muzi.com/soapserver/weather";

    // inject your business service
    private final WeatherService weatherService;

    public WeatherEndpoint(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // constructor…

    // 1) Incoming payload mapping
    @PayloadRoot(namespace = NAMESPACE, localPart = "getWeatherRequest")
    @ResponsePayload
    public JAXBElement<GetWeatherResponse> handleGetWeather( 
            @RequestPayload JAXBElement<GetWeatherRequest> requestElement) {
        // 2) Unwrap request
        GetWeatherRequest req = requestElement.getValue();

        // 3) Delegate to service
        WeatherData data = weatherService.fetchWeatherData(req.getCity());

        // 4) Build response object
        GetWeatherResponse resp = new GetWeatherResponse();
        resp.setCity(req.getCity());
        resp.setTemperature(data.getTemperature());
        resp.setDescription(data.getDescription());

        // 5) Wrap–up in JAXBElement
        ObjectFactory of = new ObjectFactory();
        return of.createGetWeatherResponse(resp);
    }
}
