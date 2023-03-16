package com.example.weather_analyzer.exception;

public class NoWeatherDataException extends IllegalArgumentException{

    public NoWeatherDataException(String message) {
        super(message);
    }

}
