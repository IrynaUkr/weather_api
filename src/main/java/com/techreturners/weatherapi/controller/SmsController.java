package com.techreturners.weatherapi.controller;

import com.techreturners.weatherapi.model.Advice;
import com.techreturners.weatherapi.model.Weather;
import com.techreturners.weatherapi.service.WeatherManagerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/weather/sms")
public class SmsController {
    @Autowired
    private Environment env;
    private final WeatherManagerService weatherManagerService;

    @GetMapping(value = "/sendSMS/{location}")
    public ResponseEntity<String> sendSMS(@PathVariable String location,
                                          @RequestParam long phoneNumber) {
        Weather weather = weatherManagerService.getCurrent(location);
        Advice advice = weatherManagerService.generateAdvice(weather);

        Twilio.init(env.getProperty("twilio.account.sid"), env.getProperty("twilio.auth.token"));
        String message = location + " " + advice.getTempAdvice();
        Message.creator(new PhoneNumber("+" + Long.toString(phoneNumber)),
                new PhoneNumber(env.getProperty("twilio.sender.phone.number")), message).create();
        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
