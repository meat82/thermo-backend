package com.runoi.thermobackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.runoi.thermobackend.entities.Temperature;
import com.runoi.thermobackend.repositories.TemperatureRepository;
import com.runoi.thermobackend.services.TemperatureService;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class TempereatureServiceTest {
    
    @Autowired
    public TemperatureService service;

    @Autowired
    public TemperatureRepository repository;

    @Test
    @DisplayName("Find all temperatures")
    public void findAllTest(){
        //given
        var date1 = LocalDateTime.of(2023, 1, 20, 0, 0, 0);
        var temperature1 = Temperature.builder().dateTime(date1).temperatureValue(10.5).build();
        var temperature2 = Temperature.builder().dateTime(date1).temperatureValue(10.5).build();
        var temperature3 = Temperature.builder().dateTime(date1).temperatureValue(10.5).build();
        var temperatures = Arrays.asList(temperature1, temperature2, temperature3);
        //when
        repository.saveAllAndFlush(temperatures);
        //then
        var temperaturesFromRepo = service.getAllTemperatures();
        System.out.println(temperaturesFromRepo);
        assertEquals(temperatures.size(),temperaturesFromRepo.size());
    }

    @Test
    @DisplayName("Find temperatures by date")
    public void findByDateTest(){
        //given
        var date1 = LocalDateTime.of(2023, 1, 20, 0, 0, 0);
        var date2 = LocalDateTime.of(2023, 1, 21, 0, 0, 0);
        var temperature1 = Temperature.builder().dateTime(date1).temperatureValue(10.5).build();
        var temperature2 = Temperature.builder().dateTime(date1).temperatureValue(10.5).build();
        var temperature3 = Temperature.builder().dateTime(date2).temperatureValue(10.5).build();
        var temperatures = Arrays.asList(temperature1, temperature2, temperature3);
        //when
        repository.saveAllAndFlush(temperatures);
        //then

    }
}
