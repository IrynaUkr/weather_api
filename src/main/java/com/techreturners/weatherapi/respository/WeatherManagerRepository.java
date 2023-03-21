package com.techreturners.weatherapi.respository;

import com.techreturners.weatherapi.model.Advice;
import org.springframework.data.repository.CrudRepository;
import com.techreturners.weatherapi.model.Advice;


public interface WeatherManagerRepository extends CrudRepository<Advice, Long> {
}

