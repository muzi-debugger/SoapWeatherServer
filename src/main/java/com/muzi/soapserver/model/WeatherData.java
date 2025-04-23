package com.muzi.soapserver.model;


public class WeatherData {
    
    private double temperature;
    private String description;
    private String errorMessage;

    public WeatherData() {}

    public WeatherData(double temperature, String description) {
        this.temperature = temperature;
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
