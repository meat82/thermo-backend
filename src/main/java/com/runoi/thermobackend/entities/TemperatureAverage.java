package com.runoi.thermobackend.entities;

import lombok.Data;

import java.util.List;

@Data
public class TemperatureAverage {
    private Double average;
    private List<Double> temperatures;
}
