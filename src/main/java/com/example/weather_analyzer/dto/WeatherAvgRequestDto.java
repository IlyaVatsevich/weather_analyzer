package com.example.weather_analyzer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@AllArgsConstructor
@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class WeatherAvgRequestDto {

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fromDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate toDate;

}
