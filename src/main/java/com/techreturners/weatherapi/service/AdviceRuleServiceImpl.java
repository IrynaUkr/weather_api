package com.techreturners.weatherapi.service;

import com.techreturners.weatherapi.exception.AdviceRuleNotFoundException;
import com.techreturners.weatherapi.model.AdviceRule;
import com.techreturners.weatherapi.respository.AdviceRuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdviceRuleServiceImpl implements AdviceRuleService {
    private final AdviceRuleRepository adviceRuleRepository;

    @Override
    public AdviceRule addAdviceRule(AdviceRule rule) {
        return adviceRuleRepository.save(rule);
    }

    @Override
    public List<AdviceRule> getAll() {
        return adviceRuleRepository.findAll();
    }

    @Override
    public AdviceRule updateRule(Long id, AdviceRule rule) {
        AdviceRule adviceRuleById = adviceRuleRepository.findAdviceRuleById(id);
        if (rule.getAdvice() != null) {
            adviceRuleById.setAdvice(rule.getAdvice());
        }
        if (rule.getHighest() != null) {
            adviceRuleById.setHighest(rule.getHighest());
        }
        if (rule.getLowest() != null) {
            adviceRuleById.setLowest(rule.getLowest());
        }
        if (rule.getCategory() != null) {
            adviceRuleById.setCategory(rule.getCategory());
        }
        return adviceRuleRepository.save(adviceRuleById);
    }

    @Override
    public void deleteById(Long id) {
        adviceRuleRepository.findById(id).orElseThrow(() -> new AdviceRuleNotFoundException("advice with id not found " + id));
    }

}
