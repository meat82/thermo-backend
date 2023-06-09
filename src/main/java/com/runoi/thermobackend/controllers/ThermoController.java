package com.runoi.thermobackend.controllers;

import com.runoi.thermobackend.entities.Temperature;
import com.runoi.thermobackend.entities.TemperatureAverage;
import com.runoi.thermobackend.execeptions.InvalidTemperatureExeception;
import com.runoi.thermobackend.services.TemperatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.NotImplementedException;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("temperatures")
@RequiredArgsConstructor
public class ThermoController {

    private final TemperatureService service;

    @Operation(summary = "Get all temperature")
    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Temperature>> getAllValues(){
        log.info("Get all temperatures");
        try {
            var temperatures = service.getAllTemperatures();
            return new ResponseEntity<>(temperatures, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    @Operation(summary = "Get temperature by date")
    @GetMapping(value = "/date")
    public ResponseEntity<List<Temperature>> getValuesByDate(
            @RequestParam(value = "year") @Schema(example = "2023") String year,
            @RequestParam(value = "month", required = false) @Schema(example = "05") Optional<String> month,
            @RequestParam(value = "day", required = false) @Schema(example = "01") Optional<String> day)
    {
        log.info("Get value by date {} {} {}", year, month.orElse(""), day.orElse(""));
        List<Temperature> response = service.getTemperaturesByDate(year, month.orElse(""), day.orElse(""));
        log.info("response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get temperature between dates. E.g startDay=2023-05-01, endDay=2023-05-06")
    @GetMapping(value = "/date/between")
    public ResponseEntity<List<Temperature>> getValuesBetweenDates(
            @RequestParam(value = "startDay") @Schema(example = "2023-05-01") String startDay,
            @RequestParam(value = "endDay") @Schema(example = "2023-05-02") String endDay)
    {
        log.info("Get temperatures between dates {} {}", startDay, endDay);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Get temperature average between dates. E.g startDay=2023-05-01, endDay=2023-05-06")
    @GetMapping(value = "/date/between/avg")
    public ResponseEntity<List<Temperature>> getAverageBetweenDates(
            @RequestParam(value = "startDay") @Schema(example = "2023-05-01") String startDay,
            @RequestParam(value = "endDay") @Schema(example = "2023-05-02") String endDay)
    {
        log.info("Get temperature average between dates {} {}", startDay, endDay);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Get temperature average by date")
    @GetMapping(value = "/date/avg")
    public ResponseEntity<TemperatureAverage> getTemperatureAgvByDate(
            @RequestParam(value = "year") @Schema(example = "2023") String year,
            @RequestParam(value = "month", required = false) @Schema(example = "05") Optional<String> month,
            @RequestParam(value = "day", required = false) @Schema(example = "01") Optional<String> day)
    {
        log.info("Get average temperature by date {} {} {}", year, month.orElse(""), day.orElse(""));
        List<Temperature> temperatures = service.getTemperaturesByDate(year, month.orElse(""), day.orElse(""));
        var total = temperatures.stream()
                .map(Temperature::getTemperatureValue)
                .reduce((double) 0, Double::sum);
        var response = TemperatureAverage.builder().average(total / temperatures.size()).temperatures(temperatures.stream().map(Temperature::getTemperatureValue).toList()).build();
        log.info("response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Temperature> addTemperature(@RequestBody TemperatureRequest request){
        log.info("Add temperature: {}", request);
        if(request.getTemperatureValue() == null)
            throw new InvalidTemperatureExeception("Missing temperature");

        var temperatureSaved = service.saveTemperature(request.toEntity());
        return new ResponseEntity<>(temperatureSaved, HttpStatus.OK);
    }

    @Value
    public static class TemperatureRequest {

        @Schema(example = "10.5", requiredMode = Schema.RequiredMode.REQUIRED)
        private Double temperatureValue;

        @Schema(example = "some note", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private String note;

        public Temperature toEntity(){
            return Temperature.builder().temperatureValue(temperatureValue).note(note).build();
        }
    }


    @Schema(name = "year", example = "2023", description = "year", requiredMode = Schema.RequiredMode.REQUIRED)
    private String year;

    @Schema(name = "month", example = "05", description = "month")
    private String month;

    @Schema(name = "day", example = "01", description = "day")
    private String day;


}
