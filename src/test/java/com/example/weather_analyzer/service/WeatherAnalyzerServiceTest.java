package com.example.weather_analyzer.service;

import com.example.weather_analyzer.dto.WeatherAvgRequestDto;
import com.example.weather_analyzer.dto.WeatherAvgResponseDto;
import com.example.weather_analyzer.dto.WeatherCurrentDto;
import com.example.weather_analyzer.entity.WeatherData;
import com.example.weather_analyzer.exception.NoWeatherDataException;
import com.example.weather_analyzer.mapper.WeatherDataMapper;
import com.example.weather_analyzer.repository.WeatherDataRepository;
import com.example.weather_analyzer.service.impl.WeatherAnalyzerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static com.example.weather_analyzer.generator.WeatherDataGenerator.generateWeatherData;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeatherAnalyzerServiceTest {

    @InjectMocks
    WeatherAnalyzerServiceImpl weatherAnalyzerService;

    @Mock
    WeatherDataRepository weatherDataRepository;

    @Mock
    WeatherDataMapper weatherDataMapper;

    private WeatherData weatherData;

    private WeatherCurrentDto weatherCurrentDto;

    @BeforeEach
    void setup() {
        weatherData = generateWeatherData();
        weatherCurrentDto = fromEntityToDto(weatherData);
    }

    @Test
    void testGetCurrentWeatherShouldReturnCurrentWeather() {
        given(weatherDataRepository.findTopByOrderByIdDesc()).willReturn(weatherData);
        given(weatherDataMapper.fromEntityToDto(weatherData)).willReturn(weatherCurrentDto);
        WeatherCurrentDto currentWeather = weatherAnalyzerService.getCurrentWeather();
        assertNotNull(currentWeather);
    }

    @Test
    void testGetAvgWeatherForPeriodShouldReturnAvgTemperature() {
        WeatherData w1 = generateWeatherData();
        LocalDate from = LocalDate.of(2023, 1, 23);
        w1.setDate(from);
        w1.setTemperature(50.0);
        WeatherData w2 = generateWeatherData();
        LocalDate to = LocalDate.of(2023, 2, 22);
        w2.setDate(to);
        w2.setTemperature(25.0);
        List<WeatherData> weatherDataList = List.of(w1,w2);
        given(weatherDataRepository.findByDateBetween(from,to)).willReturn(weatherDataList);
        given(weatherDataRepository.existsByDate(from)).willReturn(true);
        given(weatherDataRepository.existsByDate(to)).willReturn(true);
        WeatherAvgResponseDto avgWeatherForPeriod = weatherAnalyzerService.getAvgWeatherForPeriod(weatherAvgRequestDto());
        assertEquals(37.5,avgWeatherForPeriod.getAverageTemperature());
    }

    @Test
    void testGetAvgWeatherForPeriodShouldThrowCauseOfFromDateIsAfterToDate() {
        LocalDate from = LocalDate.of(2023, 3, 23);
        LocalDate to = LocalDate.of(2023, 2, 22);
        WeatherAvgRequestDto weatherAvgRequestDto = new WeatherAvgRequestDto(from, to);
        assertThrows(IllegalArgumentException.class,()->weatherAnalyzerService.getAvgWeatherForPeriod(weatherAvgRequestDto));
    }

    @Test
    void testGetAvgWeatherForPeriodShouldThrowCauseOfNoDataForPeriod() {
        LocalDate from = LocalDate.of(2023, 1, 23);
        LocalDate to = LocalDate.of(2023, 2, 22);
        given(weatherDataRepository.existsByDate(from)).willReturn(false);
        WeatherAvgRequestDto weatherAvgRequestDto = new WeatherAvgRequestDto(from, to);
        assertThrows(NoWeatherDataException.class,()-> weatherAnalyzerService.getAvgWeatherForPeriod(weatherAvgRequestDto));
    }

    private WeatherCurrentDto fromEntityToDto(WeatherData weatherData) {
        return WeatherCurrentDto.builder()
                .withTemperatureC(weatherData.getTemperature())
                .withWindSpeedMph(weatherData.getWindSpeed())
                .withPressureMb(weatherData.getPressure())
                .withLocation(weatherData.getLocation())
                .withCondition(weatherData.getCondition())
                .withAirHumidity(weatherData.getAirHumidity()).build();
    }

    private WeatherAvgRequestDto weatherAvgRequestDto() {
        LocalDate from = LocalDate.of(2023, 1, 23);
        LocalDate to = LocalDate.of(2023, 2, 22);
        return WeatherAvgRequestDto.builder().withFromDate(from).withToDate(to).build();
    }

}
