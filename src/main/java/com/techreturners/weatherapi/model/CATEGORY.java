package com.techreturners.weatherapi.model;

public enum CATEGORY {
    TEMPERATURE(1), WIND(2),RAIN(3), HUMIDITY(4);

    int value;
    private CATEGORY(int value){
        this.value = value;
    }

    public static CATEGORY lookup(int ruleTyp){
        for (CATEGORY c: CATEGORY.values()){
            if (ruleTyp == c.value){
                return c;
            }
        }
        return null;
    }
}
