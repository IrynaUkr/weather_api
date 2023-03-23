package com.techreturners.weatherapi.service;

import com.techreturners.weatherapi.model.AdviceRule;
import java.util.List;

public interface WeatherManagerAdviceService {

    List<AdviceRule> getAllAdviceRules();
    AdviceRule insertAdviceRule(AdviceRule adviceRule);
    AdviceRule getAdviceRuleById(Long adviceRuleId);
    void updateAdviceRuleById(Long adviceRuleId, AdviceRule adviceRule);
    void deleteAdviceRuleById(Long adviceRuleId);

}
