package com.techreturners.weatherapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatherapi.model.Weather;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class WeatherManagerServiceImpl implements WeatherManagerService {
    private static final String API_KEY = "388c10d917e345b9a53130959231503";
    private static final String API_URI = "https://api.weatherapi.com/v1/";

    @Override
    public  Weather getCurrent(String location){
        Weather weather = null;

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
            throw new RuntimeException(e);
        }

        return weather;
    }






}
