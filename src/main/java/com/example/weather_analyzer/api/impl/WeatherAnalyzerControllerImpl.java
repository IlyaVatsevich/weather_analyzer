package com.example.weather_analyzer.api.impl;

import com.example.weather_analyzer.api.WeatherAnalyzerControllerApi;
import com.example.weather_analyzer.dto.WeatherAvgRequestDto;
import com.example.weather_analyzer.dto.WeatherAvgResponseDto;
import com.example.weather_analyzer.dto.WeatherCurrentDto;
import com.example.weather_analyzer.service.WeatherAnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherAnalyzerControllerImpl implements WeatherAnalyzerControllerApi {

    private final WeatherAnalyzerService weatherAnalyzerService;

    @Override
    public ResponseEntity<WeatherCurrentDto> getCurrentWeather() {
        return ResponseEntity.ok(weatherAnalyzerService.getCurrentWeather());
    }

    @Override
    public ResponseEntity<WeatherAvgResponseDto> getAvgWeatherForPeriod(WeatherAvgRequestDto weatherAvgRequestDto) {
        return ResponseEntity.ok(weatherAnalyzerService.getAvgWeatherForPeriod(weatherAvgRequestDto));
    }

}
