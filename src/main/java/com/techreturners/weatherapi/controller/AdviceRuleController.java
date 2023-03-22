package com.techreturners.weatherapi.controller;

import com.techreturners.weatherapi.model.AdviceRule;
import com.techreturners.weatherapi.service.AdviceRuleServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/advice")
public class AdviceRuleController {
     private final AdviceRuleServiceImpl adviceRuleService;

     public AdviceRuleController(AdviceRuleServiceImpl adviceRuleService) {
          this.adviceRuleService = adviceRuleService;
     }

     @GetMapping
     public ResponseEntity<List<AdviceRule>> getAll() {
          List<AdviceRule> rules = adviceRuleService.getAll();
          return new ResponseEntity<>(rules, HttpStatus.OK);
     }
     @PostMapping
     public ResponseEntity<AdviceRule> addRule(@RequestBody AdviceRule rule) {
          AdviceRule newRule = adviceRuleService.addAdviceRule(rule);
          HttpHeaders httpHeaders = new HttpHeaders();
          httpHeaders.add("rule", "/api/v1/rule/" + newRule.getId().toString());
          return new ResponseEntity<>(newRule, httpHeaders, HttpStatus.CREATED);
     }
     @PutMapping({"/{adviceId}"})
     public ResponseEntity<AdviceRule> updateById(@PathVariable("adviceId") Long id, @RequestBody AdviceRule rule) {
          AdviceRule adviceRule = adviceRuleService.updateRule(id, rule);
          return new ResponseEntity<>(adviceRule, HttpStatus.OK);
     }
     @DeleteMapping("/{id}")
     public void deleteById(@PathVariable Long id){
          adviceRuleService.deleteById(id);
     }
}
