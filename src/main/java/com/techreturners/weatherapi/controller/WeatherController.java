package com.techreturners.weatherapi.controller;
import com.techreturners.weatherapi.model.Weather;
import com.techreturners.weatherapi.service.WeatherManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WeatherController {

    @Autowired
    WeatherManagerService weatherManagerService;


    @GetMapping("/weather")
    public String weatherForm(Model model) {
        model.addAttribute("weather", new Weather());
        return "weather_request";
    }

    @PostMapping("/weather")
    public String weatherSubmit(@ModelAttribute Weather weather, Model model) {
        Weather result = weatherManagerService.getCurrent(weather.getLocation().getName());
        model.addAttribute("weather", result);
        return "weather_result";
    }

}
