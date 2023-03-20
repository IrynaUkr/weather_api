package com.techreturners.weatherapi.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Current {
    Double temp_c;
    Integer is_day;
    Condition condition;
    Double wind_mph;
    Double humidity;
}
