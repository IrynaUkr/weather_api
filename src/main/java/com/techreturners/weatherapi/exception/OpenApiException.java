package com.techreturners.weatherapi.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OpenApiException {
    OpenApiError error;
}
