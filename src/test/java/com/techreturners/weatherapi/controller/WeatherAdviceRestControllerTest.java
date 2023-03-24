package com.techreturners.weatherapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.weatherapi.model.AdviceRule;
import com.techreturners.weatherapi.service.WeatherManagerAdviceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class WeatherAdviceRestControllerTest {
    @InjectMocks
    WeatherAdviceRestController controller;
    @Mock
    WeatherManagerAdviceServiceImpl service;
    @Autowired
    private MockMvc mockMvcController;
    List<AdviceRule> rules;
    AdviceRule rule;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mockMvcController = MockMvcBuilders.standaloneSetup(controller).build();
        rule = new AdviceRule(1L, 1, "lowest", "highest", "advice");
        rules = new ArrayList<>();
        rules.add(rule);
    }

    @Test
    void getAllAdviceRules() throws Exception {
        when(service.getAllAdviceRules()).thenReturn(rules);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/weather/admin/advice-rule"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].highest").value("highest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].advice").value("advice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lowest").value("lowest"));
    }

    @Test
    void getAdviceRuleById() throws Exception {
        when(service.getAdviceRuleById(rule.getId())).thenReturn(rule);
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/weather/admin/advice-rule/" + rule.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.highest").value("highest"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.advice").value("advice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lowest").value("lowest"));
    }

    @Test
    void addAdviceRule() throws Exception {
        when(service.insertAdviceRule(rule)).thenReturn(rule);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/weather/admin/advice-rule/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(rule)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        verify(service).insertAdviceRule(rule);

    }

    @Test
    void updateAdviceRuleById() throws Exception {

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/weather/admin/advice-rule/" + rule.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(rule)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service).updateAdviceRuleById(rule.getId(), rule);
    }

    @Test
    void deleteAdviceRuleById() throws Exception {
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/api/v1/weather/admin/advice-rule/" + rule.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(service).deleteAdviceRuleById(rule.getId());
    }

}
