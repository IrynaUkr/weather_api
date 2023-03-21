package com.techreturners.weatherapi.service;

import com.techreturners.weatherapi.model.AdviceRule;
import com.techreturners.weatherapi.model.Advice;
import com.techreturners.weatherapi.model.Weather;
import com.techreturners.weatherapi.respository.WeatherManagerRepository;
import com.techreturners.weatherapi.service.WeatherManagerServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


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
            assertEquals("Clear", weather.getCurrent().getCondition().getText());

    }

    @Test
    public void testGetAdviceRuleByCity(){

        AdviceRule AdviceRule1 = new AdviceRule(1L, 1, "1","9.9", "#Temperature is cold, wear an overcoat ");
        AdviceRule AdviceRule2 = new AdviceRule(2L, 1, "10","20.9", "#Temperature is chilly, wear a light jacket ");
        AdviceRule AdviceRule3 = new AdviceRule(3L, 1, "21","29.9", "#Temperature is warm, wear a shirt ");
        AdviceRule AdviceRule4 = new AdviceRule(4L, 1, "30","99", "#Temperature is very hot, drink more water ");

        AdviceRule AdviceRule5 = new AdviceRule(5L, 2, "0.1","7.9", "#No wind... ");
        AdviceRule AdviceRule6 = new AdviceRule(6L, 2, "8","19.9", "#Gentle Breeze... ");
        AdviceRule AdviceRule7 = new AdviceRule(7L, 2, "20","999", "#Very strong wind, drive slowly... ");

        AdviceRule AdviceRule8 = new AdviceRule(8L, 3, "0.1","7.9", "#It is sunny; wear a UV protection eyeglass. ");
        AdviceRule AdviceRule9 = new AdviceRule(9L, 3, "8","19.9", "#It is cloudy and may rain; bring an umbrella. ");
        AdviceRule AdviceRule10 = new AdviceRule(10L, 3, "20","999", "#It has sunlight  and good visibility. ");

        AdviceRule AdviceRule11 = new AdviceRule(11L, 4, "0.0","29.9", "#Dry humidity, drink enough water ");
        AdviceRule AdviceRule12 = new AdviceRule(12L, 4, "30","50.0", "#Good humidity for health and Comfort ");
        AdviceRule AdviceRule13= new AdviceRule(13L, 4, "51","64.9", "#Sticky Humidity. ");
        AdviceRule AdviceRule14 = new AdviceRule(14L, 4, "65","100", "#Very Humid.");
//        moweatherAdvisorRepository.save(AdviceRule);
        List<AdviceRule> AdviceRuleL = new ArrayList<>();
        AdviceRuleL.add(AdviceRule1);
        AdviceRuleL.add(AdviceRule2);
        AdviceRuleL.add(AdviceRule3);
        AdviceRuleL.add(AdviceRule4);
        AdviceRuleL.add(AdviceRule5);
        AdviceRuleL.add(AdviceRule6);
        AdviceRuleL.add(AdviceRule7);
        AdviceRuleL.add(AdviceRule8);
        AdviceRuleL.add(AdviceRule9);
        AdviceRuleL.add(AdviceRule10);
        AdviceRuleL.add(AdviceRule11);
        AdviceRuleL.add(AdviceRule12);
        AdviceRuleL.add(AdviceRule13);
        AdviceRuleL.add(AdviceRule14);


       when(mockWeatherManagerRepository.findAll()).thenReturn(AdviceRuleL);

        Weather weather = weatherManagerServiceImpl.getCurrent("Frankfurt");
        Advice advice = weatherManagerServiceImpl.generateAdvice(weather);
        assertEquals("#Temperature is warm, wear a shirt ", advice.getTempAdvice());
        assertEquals("#Gentle Breeze... ", advice.getWindAdvice());
        assertEquals("#Good humidity for health and Comfort ", advice.getHumidAdvice());
    }

    /*
    @Test
    public void testDBAdviceRule(){

        Weather weather = weatherManagerServiceImpl.getCurrent("Paris");
        Advice advice = weatherManagerServiceImpl.generateAdvice(weather);
        assertEquals("#Temperature is warm, wear a shirt ", advice.getTempAdvice());
       assertEquals("#Gentle Breeze... ", advice.getWindAdvice());


    }*/
}

