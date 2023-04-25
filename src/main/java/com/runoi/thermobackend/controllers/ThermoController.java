package com.runoi.thermobackend.controllers;

import com.runoi.thermobackend.entities.Temperature;
import com.runoi.thermobackend.execeptions.InvalidTemperatureExeception;
import com.runoi.thermobackend.services.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("temperatures")
public class ThermoController {

    @Autowired
    TemperatureService service;

    @GetMapping(value = "/all", produces = "application/json")
    public List<Temperature> getAllValues(){
        log.info("Get all temperatures");
        try {
            return service.getAllTemperatures();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }
    @PostMapping(value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Temperature addTemperature(@RequestBody Temperature temperature){
        log.info("Add temperature: {}", temperature);
        if(temperature.getTemperatureValue() == null)
            throw new InvalidTemperatureExeception("Missing temperature");
        return service.saveTemperature(temperature);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void temperatureMissing(InvalidTemperatureExeception e){
        log.error(e.getMessage());
    }
}
