package com.techreturners.weatherapi.service;

import com.techreturners.weatherapi.model.Advice;
import com.techreturners.weatherapi.model.Weather;

import java.util.List;

public interface WeatherManagerService {

    Weather getCurrent(String location);
    Advice generateAdvice(Weather weather);

    Weather getForecastForDays(String location, int numOfDays);

}
