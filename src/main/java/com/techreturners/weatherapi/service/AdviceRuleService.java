package com.techreturners.weatherapi.service;

import com.techreturners.weatherapi.model.AdviceRule;

import java.util.List;

public interface AdviceRuleService {
    AdviceRule addAdviceRule(AdviceRule rule);

    List<AdviceRule> getAll();

    AdviceRule updateRule(Long id, AdviceRule rule);

    void deleteById(Long id);

}
