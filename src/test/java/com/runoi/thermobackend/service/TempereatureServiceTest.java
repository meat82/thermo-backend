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
        var temperature1 = Temperature.builder().temperatureValue(10.5).build();
        var temperature2 = Temperature.builder().temperatureValue(10.5).build();
        var temperature3 = Temperature.builder().temperatureValue(10.5).build();
        var temperatures = Arrays.asList(temperature1, temperature2, temperature3);
        //when
        repository.saveAllAndFlush(temperatures);
        //then
        var temperaturesFromRepo = service.getAllTemperatures();
        assertEquals(temperatures.size(),temperaturesFromRepo.size());
    }

    @Test
    @DisplayName("Find temperatures by date")
    public void findByDateTest(){
        //given
        var date = LocalDateTime.of(2023, 1, 20, 0, 0, 0);
        var temperature1 = Temperature.builder().temperatureValue(10.5).build();
        var temperature2 = Temperature.builder().temperatureValue(10.5).build();
        var temperature3 = Temperature.builder().temperatureValue(10.5).build();
        var temperatures = Arrays.asList(temperature1, temperature2, temperature3);
        //when
        var saved = repository.saveAllAndFlush(temperatures);
        var changeDate = saved.get(0);
        changeDate.setDateTime(date);
        repository.saveAndFlush(changeDate);
        //then
        var byDate = service.getTemperaturesByDate("2023", "01", "20");
        assertEquals(1, byDate.size());
        byDate.stream().forEach(temperature -> {
            assertEquals(temperature.getDateTime().getYear(), date.getYear());
            assertEquals(temperature.getDateTime().getMonthValue(), date.getMonthValue());
            assertEquals(temperature.getDateTime().getDayOfMonth(), date.getDayOfMonth());
        });

    }
}
