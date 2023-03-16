package com.example.weather_analyzer.dto.weather_api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class Current {

    private double tempC;

    private Condition condition;

    private double windMph;

    private double pressureMb;

    private short humidity;

}
