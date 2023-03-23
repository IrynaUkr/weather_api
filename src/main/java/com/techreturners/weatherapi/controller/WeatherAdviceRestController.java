package com.techreturners.weatherapi.controller;

import com.techreturners.weatherapi.exception.AdviceRuleNotFoundException;
import com.techreturners.weatherapi.exception.DuplicateAdviceRuleException;
import com.techreturners.weatherapi.model.AdviceRule;
import com.techreturners.weatherapi.service.WeatherManagerAdviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/weather/admin/advice-rule")
public class WeatherAdviceRestController {
    private final WeatherManagerAdviceService weatherManagerAdviceService;
    @GetMapping
    public ResponseEntity<List<AdviceRule>> getAllAdviceRules() {
        List<AdviceRule> advice = weatherManagerAdviceService.getAllAdviceRules();
        return new ResponseEntity<>(advice, HttpStatus.OK);
    }

    @GetMapping({"/{advice-rule-id}"})
    public ResponseEntity<AdviceRule> getAdviceRuleById(@PathVariable ("advice-rule-id") Long adviceRuleId) {
        AdviceRule adviceRule = weatherManagerAdviceService.getAdviceRuleById(adviceRuleId);
        if (adviceRule == null) {
            throw new AdviceRuleNotFoundException();
        }

        return new ResponseEntity<>(adviceRule, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdviceRule> addAdviceRule(@RequestBody AdviceRule adviceRule) {
        if(weatherManagerAdviceService.getAdviceRuleById(adviceRule.getId()) != null) throw new DuplicateAdviceRuleException();
        AdviceRule newAdviceRule = weatherManagerAdviceService.insertAdviceRule(adviceRule);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("advice-rule", "api/v1/weather/admin/advice-rule" + newAdviceRule.getId().toString());
        return new ResponseEntity<>(newAdviceRule, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping({"/{advice-rule-id}"})
    public ResponseEntity<AdviceRule> updateAdviceRuleById(@PathVariable("advice-rule-id") Long adviceRuleId, @RequestBody AdviceRule adviceRule) {
     //   if(weatherManagerAdviceService.getBookById(bookId) == null)throw new BookNotFoundException();
        weatherManagerAdviceService.updateAdviceRuleById(adviceRuleId, adviceRule);
        return new ResponseEntity<>(weatherManagerAdviceService.getAdviceRuleById(adviceRuleId), HttpStatus.OK);
    }

    @DeleteMapping ({"/{advice-rule-id}"})
    public void deleteAdviceRuleById(@PathVariable("advice-rule-id") Long adviceRuleId) {
        weatherManagerAdviceService.deleteAdviceRuleById(adviceRuleId);
    }

}
