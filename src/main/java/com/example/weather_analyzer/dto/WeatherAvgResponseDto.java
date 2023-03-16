package com.example.weather_analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class WeatherAvgResponseDto {

    private double averageTemperature;

}
