package com.techreturners.weatherapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatherapi.exception.WeatherNotCreatedException;
import com.techreturners.weatherapi.model.Weather;
import com.techreturners.weatherapi.model.AdviceRule;
import com.techreturners.weatherapi.model.Advice;
import com.techreturners.weatherapi.respository.WeatherManagerRepository;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherManagerServiceImpl implements WeatherManagerService {
    private static final String API_KEY = "388c10d917e345b9a53130959231503";
    private static final String API_URI = "https://api.weatherapi.com/v1/";

    @Autowired
    WeatherManagerRepository weatherManagerRepository;
    @Override
    public  Weather getCurrent(String location){
        Weather weather;
        if (location.isEmpty()) {
            throw new WeatherNotCreatedException("the location is not valid");
        }

        try {
            HttpGet httpGet = new HttpGet(API_URI + "current.json");
            httpGet.addHeader("key", API_KEY);
            URI uri = null;
            uri = new URIBuilder(httpGet.getURI())
                    .addParameter("q", location)
                    .build();
            httpGet.setURI(uri);
            CloseableHttpClient client = HttpClientBuilder.create().build();
            CloseableHttpResponse response = client.execute(httpGet);
            String bodyAsString = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            weather = objectMapper.readValue(bodyAsString, Weather.class);
            client.close();
        } catch (URISyntaxException | IOException e) {
            throw new WeatherNotCreatedException("the weather was not created");
        }
        return weather;
    }


    @Override
    public Advice generateAdvice(Weather weather) {
        //read AdviceRule table from repository
        String tempMsg = "";
        String windMsg = "";
        String condMsg = "";
        String humidMsg = "";

        List<AdviceRule> adviceList = new ArrayList<>();

        weatherManagerRepository.findAll().forEach(adviceList::add);
        //get current temperature, current wind;
        double curTemperature = weather.getCurrent().getTemp_c();
        double curWind = weather.getCurrent().getWind_mph();
        double curHumid = weather.getCurrent().getHumidity();


        for (int i=0;i<adviceList.size(); i++){
            AdviceRule adviceRule = adviceList.get(i);
            switch (adviceRule.getCategory()){
                case 1: //category 1 is for temperature
                    //if temperature within range, insert advice
                    if ((curTemperature >= Double.valueOf(adviceRule.getLowest())) && (curTemperature <= Double.valueOf(adviceRule.getHighest()))) {
                        tempMsg += adviceRule.getAdvice();
                    }
                    break;
                case 2: //category 2 is for windy condition
                    if ((curWind >= Double.valueOf(adviceRule.getLowest())) && (curWind <= Double.valueOf(adviceRule.getHighest()))) {
                        windMsg += adviceRule.getAdvice();
                    }
                    break;
                case 3:
                    break;
                case 4:
                    if ((curHumid >= Double.valueOf(adviceRule.getLowest())) && (curHumid <= Double.valueOf(adviceRule.getHighest()))) {
                        humidMsg += adviceRule.getAdvice();
                    }
                    break;
                default:
                    break;
            }
        }

        return (new Advice(tempMsg, windMsg, condMsg, humidMsg));
    }





}
