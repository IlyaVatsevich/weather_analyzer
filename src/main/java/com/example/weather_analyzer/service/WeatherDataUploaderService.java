package com.example.weather_analyzer.service;

import com.example.weather_analyzer.dto.weather_api.WeatherApiResponseDto;
import com.example.weather_analyzer.entity.WeatherData;
import com.example.weather_analyzer.mapper.WeatherDataMapper;
import com.example.weather_analyzer.repository.WeatherDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherDataUploaderService {

    private final WeatherDataRepository weatherDataRepository;
    private final WebClient webClient;
    private final WeatherDataMapper weatherDataMapper;

    @Scheduled(fixedRateString = "${scheduled-period.fixed-rate}" ,initialDelay = 1000L)
    @Transactional
    public void uploadWeatherData() {
        WeatherApiResponseDto apiResponse = webClient.get()
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().is2xxSuccessful()) {
                        return clientResponse.bodyToMono(WeatherApiResponseDto.class);
                    } else {
                        return Mono.error(IllegalStateException::new);
                    }
                })
                .doOnError(error -> log.error("An error has occurred - {}", error.getMessage()))
                .timeout(Duration.ofSeconds(60L))
                .block();

        if (apiResponse!=null) {
            WeatherData actualWeatherData = weatherDataMapper.fromDtoToEntity(apiResponse);
            WeatherData lastWeatherDataFromDB = weatherDataRepository.findTopByOrderByIdDesc();
            if (!actualWeatherData.equals(lastWeatherDataFromDB)) {
                weatherDataRepository.save(actualWeatherData);
            }
        }
    }
}
