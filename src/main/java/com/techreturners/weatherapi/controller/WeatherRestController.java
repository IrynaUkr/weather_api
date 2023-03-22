package com.techreturners.weatherapi.controller;

import com.techreturners.weatherapi.model.Advice;
import com.techreturners.weatherapi.model.Weather;
import com.techreturners.weatherapi.service.WeatherManagerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/weather")
public class WeatherRestController {
        private final WeatherManagerService weatherManagerService;

        @GetMapping({"/current/{location}"})
        public ResponseEntity<Advice> getCurrentWeatherByLocation(@PathVariable String location) {
            Weather weather = weatherManagerService.getCurrent(location);
            Advice advice = weatherManagerService.generateAdvice(weather);
            return new ResponseEntity<>(advice, HttpStatus.OK);
        }

        @GetMapping({"/forecast/{location}"})
        public ResponseEntity<Weather> getForecastWeatherByLocation(@PathVariable String location,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date ) {
            Weather weather = weatherManagerService.getCurrent(location);
            return new ResponseEntity<>(weather, HttpStatus.OK);
        }
}
