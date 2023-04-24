package com.runoi.thermobackend.controllers;

import com.runoi.thermobackend.entities.Temperature;
import com.runoi.thermobackend.repositories.TemperatureRepository;
import com.runoi.thermobackend.services.TemperatureService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
        return service.saveTemperature(temperature);
    }

}
