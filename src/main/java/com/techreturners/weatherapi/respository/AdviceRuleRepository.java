package com.techreturners.weatherapi.respository;

import com.techreturners.weatherapi.model.AdviceRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdviceRuleRepository extends JpaRepository<AdviceRule, Long> {
    @Override
    List<AdviceRule> findAll();

    AdviceRule findAdviceRuleById(Long id);

}
