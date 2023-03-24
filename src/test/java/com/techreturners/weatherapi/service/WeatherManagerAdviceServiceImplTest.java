package com.techreturners.weatherapi.service;

import com.techreturners.weatherapi.model.AdviceRule;
import com.techreturners.weatherapi.respository.WeatherManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherManagerAdviceServiceImplTest {
    @Mock
    WeatherManagerRepository repository;
    @InjectMocks
    WeatherManagerAdviceServiceImpl service;
    List<AdviceRule> rules;
    AdviceRule rule;
    @BeforeEach
    void setUp(){
        rule = new AdviceRule(1L,1, "lowest", "highest", "advice");
        rules = new ArrayList<>();
        rules.add(rule);
    }


    @Test
    void getAllAdviceRules() {
         when(repository.findAll()).thenReturn(rules);
        List<AdviceRule> actual = service.getAllAdviceRules();

        assertEquals(rules, actual);
        verify(repository).findAll();
    }

    @Test
    void insertAdviceRule() {
        AdviceRule expectedAdviceRule =rule;
        expectedAdviceRule.setId(2L);
        when(repository.save(rule)).thenReturn(expectedAdviceRule);
        AdviceRule actalAdviceRule = service.insertAdviceRule(rule);

        assertEquals(expectedAdviceRule, actalAdviceRule);
        verify(repository).save(rule);

    }

    @Test
    void getAdviceRuleById() {
        when(repository.findById(rule.getId())).thenReturn(Optional.of(rule));

        AdviceRule actualAdviceRule = service.getAdviceRuleById(rule.getId());

        assertEquals(rule, actualAdviceRule);
        verify(repository).findById(rule.getId());
    }

    @Test
    void updateAdviceRuleById() {
        AdviceRule expected = rule;
        expected.setAdvice("new advice");
        expected.setHighest("new Highest");
        when(repository.findById(rule.getId())).thenReturn(Optional.of(expected));
        when(repository.save(expected)).thenReturn(expected);

        AdviceRule actual =  service.updateAdviceRuleById(rule.getId(), rule);

        assertEquals(expected, actual);
    }

    @Test
    void deleteAdviceRuleById() {
        service.deleteAdviceRuleById(rule.getId());
        verify(repository).deleteById(rule.getId());
    }

}