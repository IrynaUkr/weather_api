package com.techreturners.weatherapi.service;

import com.techreturners.weatherapi.model.Weather;

public interface WeatherManagerService {

    Weather getCurrent(String location);

}
