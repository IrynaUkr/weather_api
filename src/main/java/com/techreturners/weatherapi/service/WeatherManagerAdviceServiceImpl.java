package com.techreturners.weatherapi.service;

import com.techreturners.weatherapi.model.AdviceRule;
import com.techreturners.weatherapi.respository.WeatherManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherManagerAdviceServiceImpl implements WeatherManagerAdviceService {
    @Autowired
    WeatherManagerRepository weatherManagerRepository;

    @Override
    public List<AdviceRule> getAllAdviceRules() {
        List<AdviceRule> advice = new ArrayList<>();
        weatherManagerRepository.findAll().forEach(advice::add);
        return advice;
    }

    @Override
    public AdviceRule insertAdviceRule(AdviceRule adviceRule) {
        return weatherManagerRepository.save(adviceRule);
    }

    @Override
    public AdviceRule getAdviceRuleById(Long id) {

        AdviceRule adviceRule = null;

        if (id != null) {
            Optional<AdviceRule> optionalAdviceRule = weatherManagerRepository.findById(id);

            if (optionalAdviceRule.isPresent()) {
                adviceRule = optionalAdviceRule.get();
            }
        }
        return adviceRule;

    }

    @Override
    public AdviceRule updateAdviceRuleById(Long id, AdviceRule adviceRule) {
        AdviceRule retrievedAdviceRule = weatherManagerRepository.findById(id).get();

        retrievedAdviceRule.setCategory(adviceRule.getCategory());
        retrievedAdviceRule.setAdvice(adviceRule.getAdvice());
        retrievedAdviceRule.setLowest(adviceRule.getLowest());
        retrievedAdviceRule.setHighest(adviceRule.getHighest());
        return weatherManagerRepository.save(retrievedAdviceRule);
    }

    @Override
    public void deleteAdviceRuleById(Long id) {
        weatherManagerRepository.deleteById(id);
    }
}
