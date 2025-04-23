package com.muzi.soapserver.model;


public class WeatherData {
    private double temperature;
    private String description;

    public WeatherData() {}

    public WeatherData(double temperature, String description) {
        this.temperature = temperature;
        this.description = description;
    }

    // getters and setters
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

}
