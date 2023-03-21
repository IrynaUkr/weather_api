package com.techreturners.weatherapi.model;

public class ManagerResponse {
    Weather weather;
    Advice advice;

    public ManagerResponse( Weather weather, Advice advice){
        this.weather = weather;
        this.advice = advice;
    }
}
