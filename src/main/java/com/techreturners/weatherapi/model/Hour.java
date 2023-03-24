package com.techreturners.weatherapi.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Hour {

  Double temp_c;
  Double wind_mph;
  Double pressure_mb;
  @JsonFormat
  (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:MM")
  private Date time;

  Condition condition;
  
}
