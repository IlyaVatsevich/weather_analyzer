package com.example.weather_analyzer.service;

import com.example.weather_analyzer.dto.WeatherAvgRequestDto;
import com.example.weather_analyzer.dto.WeatherAvgResponseDto;
import com.example.weather_analyzer.dto.WeatherCurrentDto;

public interface WeatherAnalyzerService {

    WeatherCurrentDto getCurrentWeather();

    WeatherAvgResponseDto getAvgWeatherForPeriod(WeatherAvgRequestDto weatherAvgRequestDto);

}
