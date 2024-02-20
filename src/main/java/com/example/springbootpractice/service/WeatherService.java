package com.example.springbootpractice.service;

import com.example.springbootpractice.dto.WeatherResponse;
import com.example.springbootpractice.mapper.WeatherResponseMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    public static final String HTTPS_API_ToMORROW = "https://api.tomorrow.io/v4/weather/forecast";
    public static final String LOCATION = "42.3478,-71.0466";
    public static final String APIKEY = "FG79P2cZG2aKO9h4tOy66HCvHbT2gRpC";

    private final RestTemplate restTemplate;
    private final WeatherResponseMapper weatherResponseMapper;

    @Autowired
    public WeatherService(WeatherResponseMapper weatherResponseMapper) {
        this.restTemplate = new RestTemplate();
        this.weatherResponseMapper = weatherResponseMapper;
    }
    public WeatherResponse weatherResponse() throws JsonProcessingException {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                HTTPS_API_ToMORROW + "?location=" + LOCATION + "&apikey=" + APIKEY, String.class);
        return weatherResponseMapper.mapWeatherResponse(responseEntity.getBody());
    }

}
