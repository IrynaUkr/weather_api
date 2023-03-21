package com.techreturners.weatherapi.respository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import com.techreturners.weatherapi.model.AdviceRule;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class WeatherManagerRepositoryTests {
    @Autowired
    private WeatherManagerRepository weatherManagerRepository;

    @Test
    public void testFindAllAdviceText() {

        //AdviceRule advice1 = new AdviceRule(1L, 1, "1","9.9", "Temperature is cold, wear an overcoat");
        AdviceRule advice2 = new AdviceRule(2L, 1, "10","20", "Temperature is chilly, wear a light jacket");
        AdviceRule advice3 = new AdviceRule(3L, 2, "0.1","7.9", "No wind...");

       // weatherManagerRepository.save(advice1);
        weatherManagerRepository.save(advice2);
        weatherManagerRepository.save(advice3);

        Iterable<AdviceRule> adviceList = weatherManagerRepository.findAll();
        assertThat(adviceList).hasSize(2);
    }

}

