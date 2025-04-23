package com.muzi.soapserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration.class})
@ConfigurationPropertiesScan("com.muzi.soapserver.config")
public class WeatherSoapServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherSoapServerApplication.class, args);
	}
	

}
