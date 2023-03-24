package com.techreturners.weatherapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Forecast {

  @JsonInclude(value = Include.NON_EMPTY)
  // @JsonIgnoreProperties(ignoreUnknown = true)
  private List<Forecastday> forecastday = new ArrayList<Forecastday>();
  
}
