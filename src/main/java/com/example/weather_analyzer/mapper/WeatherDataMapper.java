package com.example.weather_analyzer.mapper;

import com.example.weather_analyzer.dto.WeatherCurrentDto;
import com.example.weather_analyzer.dto.weather_api.WeatherApiResponseDto;
import com.example.weather_analyzer.entity.WeatherData;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WeatherDataMapper {

    public WeatherCurrentDto fromEntityToDto(WeatherData weatherData) {
        return WeatherCurrentDto.builder()
                .withTemperatureC(weatherData.getTemperature())
                .withTemperatureF((weatherData.getTemperature() * 1.8) + 32)
                .withWindSpeedMph(weatherData.getWindSpeed())
                .withPressureMb(weatherData.getPressure())
                .withLocation(weatherData.getLocation())
                .withCondition(weatherData.getCondition())
                .withAirHumidity(weatherData.getAirHumidity()).build();
    }

    public WeatherData fromDtoToEntity(WeatherApiResponseDto weatherApiResponseDto) {
        return WeatherData.builder()
                .withDate(LocalDate.now())
                .withLocation(weatherApiResponseDto.getLocation().getName())
                .withCondition(weatherApiResponseDto.getCurrent().getCondition().getText())
                .withTemperature(weatherApiResponseDto.getCurrent().getTempC())
                .withPressure(weatherApiResponseDto.getCurrent().getPressureMb())
                .withAirHumidity(weatherApiResponseDto.getCurrent().getHumidity())
                .withWindSpeed(weatherApiResponseDto.getCurrent().getWindMph())
                .build();
    }
}
