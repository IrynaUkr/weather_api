package com.techreturners.weatherapi.service;

import com.techreturners.weatherapi.model.Advice;
import com.techreturners.weatherapi.model.Weather;
import com.techreturners.weatherapi.respository.WeatherManagerRepository;
import com.techreturners.weatherapi.service.WeatherManagerServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class WeatherManagerServiceTests {

    @Mock
    private WeatherManagerRepository mockWeatherManagerRepository;

    @InjectMocks
    private WeatherManagerServiceImpl weatherManagerServiceImpl;

    @Test
    public void testOpenApiGet(){
        //call open API for current weather London
        Weather weather = new Weather();
        WeatherManagerService weatherManagerService = new WeatherManagerServiceImpl();

            weather = ((WeatherManagerServiceImpl) weatherManagerService).getCurrent("Rome");
            assertEquals("Partly cloudy", weather.getCurrent().getCondition().getText());


    }

    @Test
    public void testGetAdviceByCity(){

        //    Advice advice = new Advice(1L, 1, "1","**", "It is hot, wear light cloth");
        Advice advice1 = new Advice(1L, 1, "1","9.9", "#Temperature is cold, wear an overcoat ");
        Advice advice2 = new Advice(2L, 1, "10","20.9", "#Temperature is chilly, wear a light jacket ");
        Advice advice3 = new Advice(3L, 1, "21","29.9", "#Temperature is warm, wear a shirt ");
        Advice advice4 = new Advice(4L, 2, "0.1","7.9", "#No wind... ");
        Advice advice5 = new Advice(5L, 2, "8","19.9", "#Gentle Breeze... ");
        Advice advice6 = new Advice(6L, 2, "20","999", "#Very strong wind, drive slowly... ");

//        moweatherAdvisorRepository.save(advice);
        List<Advice> adviceL = new ArrayList<>();
        adviceL.add(advice1);
        adviceL.add(advice2);
        adviceL.add(advice3);
        adviceL.add(advice4);
        adviceL.add(advice5);
        adviceL.add(advice6);

        when(mockWeatherManagerRepository.findAll()).thenReturn(adviceL);

        String message = weatherManagerServiceImpl.getAdviceForCurrent("Dubai");
        assertEquals("#Temperature is warm, wear a shirt #Gentle Breeze... ", message);

    }

}

