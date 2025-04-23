package com.muzi.soapserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration.class})
public class WeatherSoapServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherSoapServerApplication.class, args);
	}

}
