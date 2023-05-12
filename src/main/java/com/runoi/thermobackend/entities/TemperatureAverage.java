package com.runoi.thermobackend.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TemperatureAverage {
    private Double average;
    private List<Double> temperatures;
}
