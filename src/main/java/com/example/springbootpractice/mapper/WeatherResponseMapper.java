package com.example.springbootpractice.mapper;

import com.example.springbootpractice.dto.Location;
import com.example.springbootpractice.dto.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class WeatherResponseMapper {
    private final ObjectMapper jsonParser = new ObjectMapper();

    public WeatherResponse mapWeatherResponse(String str) throws JsonProcessingException {
        JsonNode jsonNode = jsonParser.readTree(str);
        return new WeatherResponse(
                getLocation(jsonNode),
                getAvgTemperature(jsonNode, "minutely", "temperature"),
                getAvgTemperature(jsonNode, "hourly", "temperature"),
                getAvgTemperature(jsonNode, "daily", "temperatureApparentAvg"));
    }

    private double getAvgTemperature(JsonNode jsonNode, String range, String nodeName) {
        return jsonNode.get("timelines").get(range).findValues(nodeName)
                .stream()
                .collect(Collectors.averagingDouble(JsonNode::asDouble));
    }

    private Location getLocation(JsonNode jsonNode) {
        JsonNode jsonNodeLocation = jsonNode.get("location");
        return new Location(jsonNodeLocation.get("lat").asText(), jsonNodeLocation.get("lon").asText());
    }
}
