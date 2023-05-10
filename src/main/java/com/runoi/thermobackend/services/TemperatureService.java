package com.runoi.thermobackend.services;

import com.runoi.thermobackend.entities.Temperature;
import com.runoi.thermobackend.repositories.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TemperatureService {

    private final TemperatureRepository repository;

    public List<Temperature> getAllTemperatures(){
        return repository.findAll();
    }

    public Temperature saveTemperature(Temperature temperature){
        return repository.saveAndFlush(temperature);
    }

    public List<Temperature> getTemperaturesByDate(String year, String month, String day) throws DateTimeParseException, NumberFormatException {
        return repository.findByDate(
                LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0),
                LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 23, 59, 59)
        );
    }
}
