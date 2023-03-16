package com.example.weather_analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class WeatherCurrentDto {

    private double temperatureC;

    private double temperatureF;

    private double windSpeedMph;

    private double pressureMb;

    private short airHumidity;

    private String condition;

    private String location;

}
