package com.runoi.thermobackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.runoi.thermobackend.entities.Temperature;
import com.runoi.thermobackend.services.TemperatureService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;

@WebMvcTest
public class ThermoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TemperatureService service;

    @Test
    public void getAllTest() throws Exception {

        BDDMockito.given(service.getAllTemperatures()).willReturn(Collections.singletonList(Temperature.builder().id(1L).temperatureValue(10.0).build()));

        mockMvc.perform(MockMvcRequestBuilders.get("/temperatures/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    public void saveTemperatureTest() throws Exception {

        var temperature = Temperature.builder().id(1L).temperatureValue(20.00).note("test").build();
        BDDMockito.given(service.saveTemperature(BDDMockito.mock(Temperature.class))).willReturn(temperature);

        mockMvc.perform(MockMvcRequestBuilders.post("/temperatures/add").content(jsonToString(temperature)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void saveTemperatureMissingTemperatureTest() throws Exception {

        var temperature = Temperature.builder().id(1L).note("test").build();
        BDDMockito.given(service.saveTemperature(BDDMockito.mock(Temperature.class))).willReturn(temperature);

        mockMvc.perform(MockMvcRequestBuilders.post("/temperatures/add").content(jsonToString(temperature)).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    String jsonToString(Temperature temperature) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(temperature);
    }
}
