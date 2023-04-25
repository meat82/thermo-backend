package com.runoi.thermobackend.services;

import com.runoi.thermobackend.entities.Temperature;
import com.runoi.thermobackend.repositories.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TemperatureService {

    @Autowired
    TemperatureRepository repository;

    public List<Temperature> getAllTemperatures(){
        return repository.findAll();
    }

    public Temperature saveTemperature(Temperature temperature){
        return repository.saveAndFlush(temperature);
    }
}
