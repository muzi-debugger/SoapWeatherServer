Weather SOAP Server

A Spring Boot-based SOAP web service that exposes a GetWeather operation that calls the OpenWeatherMap REST API to fetch real-time weather data (temperature, description) for any city/country pair.
Use Case

  Enterprise Integration
  Legacy systems or B2B partners that require SOAP (rather than REST) can integrate with this service to retrieve weather data.

  API Gateway
  Acts as a SOAP → REST gateway: incoming SOAP requests get translated into HTTP calls to OpenWeatherMap, then marshalled back into SOAP responses or SOAP Faults.

  Prerequisites

    Java 17+

    Maven 3.6+

    An account on openweathermap.org, with a valid API key.

Configuration

  Clone the repo
    ``` git clone https://github.com/muzi-debugger/SoapWeatherServer.git
    cd weather-soap-server
    ``` 
Declare your API key in the WeatherApiProperties.java class (Not ideal, ongoing issue to be resolved. The api key should be in application.properties)
``` mvn clean package ```
Run the jar: 
``` weather-soap-server-0.0.1-SNAPSHOT.jar ```
The SOAP endpoint will be available at:
  ``` http://localhost:8080/ws ```
And the WSDL at:
``` http://localhost:8080/ws/weather.wsdl ```
WSDL / Operations

Once running, retrieve the WSDL: ``` GET http://localhost:8080/ws/weather.wsdl ```

It defines a single operation getWeather:
```
  <wsdl:operation name="getWeather">
  <wsdl:input  message="tns:getWeatherRequest"/>
  <wsdl:output message="tns:getWeatherResponse"/>
</wsdl:operation>
```
Request / Response Structures
getWeatherRequest:
```
xs:element name="getWeatherRequest" type="tns:GetWeatherRequest"/>
<xs:complexType name="GetWeatherRequest">
  <xs:sequence>
    <xs:element name="city"    type="xs:string"/>
    <xs:element name="country" type="xs:string"/>
  </xs:sequence>
</xs:complexType>

```

getWeatherResponse:
```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:getWeatherResponse xmlns:ns2="http://muzi.com/weather">
            <ns2:city>Pretoria</ns2:city>
            <ns2:temperature>21.23</ns2:temperature>
            <ns2:description>light rain</ns2:description>
        </ns2:getWeatherResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
--- 
Sample Payloads

Successful Response
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:wea="http://muzi.com/weather">
  <soapenv:Header/>
  <soapenv:Body>
    <wea:getWeatherResponse>
      <wea:city>London</wea:city>
      <wea:temperature>15.23</wea:temperature>
      <wea:description>clear sky</wea:description>
    </wea:getWeatherResponse>
  </soapenv:Body>
</soapenv:Envelope>

```

Error / Fault Response

If the OpenWeatherMap API key is missing/invalid or the city is not found, a SOAP Fault is returned:

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Body>
    <soapenv:Fault>
      <faultcode>soapenv:Client</faultcode>
      <faultstring xml:lang="en">
        Unable to fetch weather for Atlantis, ZZ
      </faultstring>
    </soapenv:Fault>
  </soapenv:Body>
</soapenv:Envelope>
```



