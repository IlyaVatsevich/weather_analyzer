package com.example.weather_analyzer.api;

import com.example.weather_analyzer.dto.WeatherAvgRequestDto;
import com.example.weather_analyzer.dto.WeatherAvgResponseDto;
import com.example.weather_analyzer.dto.WeatherCurrentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/weather")
public interface WeatherAnalyzerControllerApi {

    @GetMapping
    ResponseEntity<WeatherCurrentDto> getCurrentWeather();

    @PostMapping
    ResponseEntity<WeatherAvgResponseDto> getAvgWeatherForPeriod(@RequestBody WeatherAvgRequestDto weatherAvgRequestDto);

}
