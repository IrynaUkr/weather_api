package com.techreturners.weatherapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Advice {
    private final String tempAdvice; //advice based on temperature
    private final String windAdvice; //advice based on wind speed
    private final String condAdvice; //advice based on sunny or cloudy
    private final String humidAdvice; //advice based on humiduty

    public Advice(String tempAdvice, String windAdvice, String condAdvice, String humidAdvice) {
        this.tempAdvice = tempAdvice;
        this.windAdvice = windAdvice;
        this.condAdvice = condAdvice;
        this.humidAdvice = humidAdvice;
    }
   Weather weather;
}
