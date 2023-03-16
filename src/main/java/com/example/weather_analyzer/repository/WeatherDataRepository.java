package com.example.weather_analyzer.repository;

import com.example.weather_analyzer.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeatherDataRepository extends JpaRepository<WeatherData,Long> {

    boolean existsByDate(LocalDate localDate);

    WeatherData findTopByOrderByIdDesc();

    List<WeatherData> findByDateBetween(LocalDate from, LocalDate to);
}
