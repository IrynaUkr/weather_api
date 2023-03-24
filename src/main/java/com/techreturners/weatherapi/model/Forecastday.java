package com.techreturners.weatherapi.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Forecastday {

  @JsonFormat
  (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date date;
  private Day day;
  @JsonInclude(value = Include.NON_EMPTY)
  private List<Hour> hour = new ArrayList<>();
  
}
