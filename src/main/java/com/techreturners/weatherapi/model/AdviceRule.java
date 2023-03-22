package com.techreturners.weatherapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdviceRule {
    @Id
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    Integer category;    //advice category - 1 for temperature;2 for wind; 3 for conditon; 4 for humidity

    @Column
    String lowest;   // minimum threshold for this advise, "**" if no minimum bound

    @Column
    String highest;  //maximum threshold for this advice, "**" if no maxiumn bound

    @Column
    String advice;  //text message for recommendation of this category

}
