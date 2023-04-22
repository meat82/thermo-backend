package com.runoi.thermobackend.repositories;

import com.runoi.thermobackend.entities.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<Temperature,Long> {

}
