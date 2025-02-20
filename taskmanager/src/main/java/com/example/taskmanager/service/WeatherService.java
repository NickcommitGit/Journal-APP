package com.example.taskmanager.service;

import com.example.taskmanager.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
  private static  final String apiKey="3be70042e567459ea7e104223252002";

   private static final String API="http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY";

   @Autowired
   private RestTemplate restTemplate;


  public  WeatherResponse getWeather(String city) {
    String apiURL=API.replace("CITY",city).replace("API_KEY",apiKey);
    ResponseEntity<WeatherResponse> response = restTemplate.exchange(apiURL, HttpMethod.GET,null, WeatherResponse.class);//Deserialization
    WeatherResponse Body=response.getBody();
    return Body;

  }
}
