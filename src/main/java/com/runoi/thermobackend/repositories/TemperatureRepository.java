package com.runoi.thermobackend.repositories;

import com.runoi.thermobackend.entities.Temperature;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemperatureRepository extends JpaRepository<Temperature,Long> {

    public List<Temperature> findByDateTime(LocalDateTime dateTime);

    @Query("SELECT t FROM Temperature t WHERE t.dateTime BETWEEN :dateTimeStart AND :dateTimeEnd")
    public List<Temperature> findByDate(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);

}
