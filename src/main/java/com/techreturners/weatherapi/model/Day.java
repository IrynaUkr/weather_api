package com.techreturners.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Day {

  Double maxtemp_c;
  Double mintemp_c;
  Double avgtemp_c;
  Condition condition;
  
}
