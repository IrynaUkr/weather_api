package com.techreturners.weatherapi.exception;

public class AdviceRuleNotFoundException extends RuntimeException {
    public AdviceRuleNotFoundException(){
        super();
    }
    public AdviceRuleNotFoundException(String message) {
        super(message);
    }

}
