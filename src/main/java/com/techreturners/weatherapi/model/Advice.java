package com.techreturners.weatherapi.model;

public class Advice {
    private String tempAdvice; //advice based on temperature
    private String windAdvice; //advice based on wind speed
    private String condAdvice; //advice based on sunny or cloudy

    private String humidAdvice; //advice based on humiduty

    public Advice(String tempAdvice, String windAdvice, String condAdvice, String humidAdvice ){
        this.condAdvice = condAdvice;
        this.humidAdvice = humidAdvice;
        this.tempAdvice = tempAdvice;
        this.windAdvice = windAdvice;
    }

    public String getTempAdvice(){
        return this.tempAdvice;
    }
    public String getWindAdvice(){
        return this.windAdvice;
    }

    public String getCondAdvice(){
        return this.condAdvice;
    }
    public String getHumidAdvice(){
        return this.humidAdvice;
    }
}
