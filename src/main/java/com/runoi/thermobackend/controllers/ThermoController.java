package com.runoi.thermobackend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThermoController {

    Logger logger = LoggerFactory.getLogger(ThermoController.class);

    @GetMapping("/all")
    public String getAllValues(){
        logger.info("getAll values");
        return "all";
    }

}
