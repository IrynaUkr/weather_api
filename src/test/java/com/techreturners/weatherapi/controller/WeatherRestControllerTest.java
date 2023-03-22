package com.techreturners.weatherapi.controller;
import com.techreturners.weatherapi.model.Advice;
import com.techreturners.weatherapi.model.Location;
import com.techreturners.weatherapi.model.Weather;
import com.techreturners.weatherapi.service.WeatherManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class WeatherRestControllerTest {

    private final String LONDON = "London";
    @Mock
    private WeatherManagerServiceImpl weatherManagerService;
    @InjectMocks
    private WeatherRestController controller;
    @Autowired
    private MockMvc mockMvcController;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getCurrentWeatherByLocation() throws Exception {

        Location locationLondon = new Location();
        locationLondon.setName(LONDON);
        Weather londonWeather = new Weather();
        londonWeather.setLocation(locationLondon);

        Advice advice = new Advice("temp", "wind", "cond", "humid");

        when(weatherManagerService.getCurrent(LONDON)).thenReturn(londonWeather);
        when(weatherManagerService.generateAdvice(londonWeather)).thenReturn(advice);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/weather/current/" + LONDON);
        this.mockMvcController.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(weatherManagerService).getCurrent(LONDON);
        verify(weatherManagerService).generateAdvice(londonWeather);
    }

}
