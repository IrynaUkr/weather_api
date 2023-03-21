package com.techreturners.weatherapi.respository;

import com.techreturners.weatherapi.model.AdviceRule;
import org.springframework.data.repository.CrudRepository;
import com.techreturners.weatherapi.model.AdviceRule;


public interface WeatherManagerRepository extends CrudRepository<AdviceRule, Long> {
}

