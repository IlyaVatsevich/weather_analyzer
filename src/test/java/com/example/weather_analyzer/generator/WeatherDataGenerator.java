package com.example.weather_analyzer.generator;

import com.example.weather_analyzer.entity.WeatherData;

import java.util.Random;

public class WeatherDataGenerator {

    private static final Random RND = new Random();

    public static WeatherData generateWeatherData() {
        return WeatherData.builder()
                .withAirHumidity(generateRandomShort())
                .withCondition("Partly cloudy")
                .withLocation("Minsk")
                .withPressure(generateRandomDouble())
                .withTemperature(generateRandomDouble())
                .withWindSpeed(generateRandomDouble())
                .build();
    }

    private static Short generateRandomShort() {
        return ((short) RND.ints(Short.MIN_VALUE, Short.MAX_VALUE).findAny().getAsInt());
    }

    private static Double generateRandomDouble() {
        return RND.nextDouble(100);
    }

}
