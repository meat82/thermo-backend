package com.runoi.thermobackend.controllers;

import com.runoi.thermobackend.execeptions.InvalidTemperatureExeception;
import com.runoi.thermobackend.execeptions.TemperatureErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ThermoControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { InvalidTemperatureExeception.class})
    public ResponseEntity<TemperatureErrorResponse> invalidTemperatureHandler(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        var response = TemperatureErrorResponse.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
