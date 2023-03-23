package com.techreturners.weatherapi.controller;
import com.techreturners.weatherapi.exception.AdviceRuleNotFoundException;
import com.techreturners.weatherapi.exception.ApiError;
import com.techreturners.weatherapi.exception.DuplicateAdviceRuleException;
import com.techreturners.weatherapi.exception.WeatherNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class WeatherAdviceExceptionController {
    @ExceptionHandler(AdviceRuleNotFoundException.class)
        public ResponseEntity<Object> exception(AdviceRuleNotFoundException exception) {
            return new ResponseEntity<>("Advice Rule not found", HttpStatus.NOT_FOUND);
        }
    @ExceptionHandler(DuplicateAdviceRuleException.class)
    public ResponseEntity<Object> exception(DuplicateAdviceRuleException exception) {
        return new ResponseEntity<>("Advice Rule already exists", HttpStatus.SEE_OTHER);
    }

    @ExceptionHandler(WeatherNotCreatedException.class)
    public ResponseEntity<Object> exception(WeatherNotCreatedException exception) {
        return new ResponseEntity<>(new ApiError(exception.getMessage(), HttpStatus.SEE_OTHER, LocalDateTime.now()) , HttpStatus.SEE_OTHER);
    }
}

