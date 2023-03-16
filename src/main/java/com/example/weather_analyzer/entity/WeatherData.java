package com.example.weather_analyzer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Builder(setterPrefix = "with")
@AllArgsConstructor
@Getter
@Setter
@Table(name = "weather_data")
@EqualsAndHashCode
public class WeatherData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weather_id_generator")
    @SequenceGenerator(name = "weather_id_generator", sequenceName = "weather_data_id_seq",
            allocationSize = 100,initialValue = 1000)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "temperature_c",nullable = false)
    private Double temperature;

    @Column(name = "wind_mph",nullable = false)
    private Double windSpeed;

    @Column(name = "pressure_mb",nullable = false)
    private Double pressure;

    @Column(name = "air_humidity",nullable = false)
    private Short airHumidity;

    @Column(name = "w_condition",nullable = false)
    private String condition;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "w_date",nullable = false)
    private LocalDate date;


}
