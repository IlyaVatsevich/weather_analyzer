package com.example.weather_analyzer.service.impl;

import com.example.weather_analyzer.dto.WeatherAvgRequestDto;
import com.example.weather_analyzer.dto.WeatherAvgResponseDto;
import com.example.weather_analyzer.dto.WeatherCurrentDto;
import com.example.weather_analyzer.entity.WeatherData;
import com.example.weather_analyzer.exception.NoWeatherDataException;
import com.example.weather_analyzer.mapper.WeatherDataMapper;
import com.example.weather_analyzer.repository.WeatherDataRepository;
import com.example.weather_analyzer.service.WeatherAnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeatherAnalyzerServiceImpl implements WeatherAnalyzerService {

    private final WeatherDataRepository weatherDataRepository;
    private final WeatherDataMapper weatherDataMapper;

    @Override
    public WeatherCurrentDto getCurrentWeather() {
        return weatherDataMapper.fromEntityToDto(weatherDataRepository.findTopByOrderByIdDesc());
    }

    @Override
    public WeatherAvgResponseDto getAvgWeatherForPeriod(WeatherAvgRequestDto weatherAvgRequestDto) {

        if (weatherAvgRequestDto.getFromDate().isAfter(weatherAvgRequestDto.getToDate())) {
            throw new IllegalArgumentException("'from_date' must be before 'to_date'");
        }

        if (!weatherDataRepository.existsByDate(weatherAvgRequestDto.getFromDate())
                || !weatherDataRepository.existsByDate(weatherAvgRequestDto.getToDate())) {
            throw new NoWeatherDataException("No data for this period.");
        }

        List<WeatherData> weatherDataBetweenFromTo = weatherDataRepository
                .findByDateBetween(weatherAvgRequestDto.getFromDate(), weatherAvgRequestDto.getToDate());

        Map<LocalDate, List<WeatherData>> weatherDataByDate = weatherDataBetweenFromTo.stream()
                .collect(Collectors.groupingBy(WeatherData::getDate));

        Double collect = weatherDataByDate.values()
                .stream()
                .map(weatherData -> weatherData.stream()
                        .collect(Collectors.averagingDouble(WeatherData::getTemperature)))
                .collect(Collectors.averagingDouble(Double::doubleValue));

        return new WeatherAvgResponseDto(Math.floor(collect*100)/100);
    }

}
