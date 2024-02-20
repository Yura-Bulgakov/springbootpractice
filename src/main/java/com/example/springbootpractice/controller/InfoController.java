package com.example.springbootpractice.controller;

import com.example.springbootpractice.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class InfoController {
    private final WeatherService weatherService;

    @Autowired
    public InfoController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public String getWeather() throws JsonProcessingException {
        return weatherService.weatherResponse().toString();
    }
}
