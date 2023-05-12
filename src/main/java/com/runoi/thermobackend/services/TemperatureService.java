package com.runoi.thermobackend.services;

import com.runoi.thermobackend.entities.Temperature;
import com.runoi.thermobackend.execeptions.InvalidTemperatureExeception;
import com.runoi.thermobackend.repositories.TemperatureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class TemperatureService {

    private final TemperatureRepository repository;

    public List<Temperature> getAllTemperatures(){return repository.findAll();}

    public Temperature saveTemperature(Temperature temperature){
        return repository.saveAndFlush(temperature);
    }

    public List<Temperature> getTemperaturesByDate(String year, String month, String day) {
        try {
            return repository.findByDate(getStartDate(year, month, day), getEndDate(year, month, day));
        } catch (NumberFormatException exception) {
            throw new InvalidTemperatureExeception(String.format("failed to parse %s, %s, %s", year, month, day));
        } catch (DateTimeException exception) {
            throw new InvalidTemperatureExeception("Invalid date format");
        }

    }

    private LocalDateTime getEndDate(String year, String month, String day) {
        if(StringUtils.isBlank(month) && StringUtils.isBlank(day)){
            return LocalDateTime.of(Integer.parseInt(year), Month.DECEMBER.getValue(), lastDayOfMonth("12"), 23, 59);
        } else if (StringUtils.isBlank(day)) {
            return LocalDateTime.of(Integer.parseInt(year),Integer.parseInt(month), lastDayOfMonth(month), 23, 59);
        }
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 23, 59);
    }

    private int lastDayOfMonth(String month) {
        var calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Integer.parseInt(month) -1 );
        log.info("Month {} has last day {}", month, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getActualMaximum(Calendar.DATE);
    }

    private LocalDateTime getStartDate(String year, String month, String day) {
        if(StringUtils.isBlank(month) && StringUtils.isBlank(day)){
            return LocalDateTime.of(Integer.parseInt(year),1, 1, 0, 0);
        } else if (StringUtils.isBlank(day)) {
            return LocalDateTime.of(Integer.parseInt(year),Integer.parseInt(month), 1, 0, 0);
        }
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
    }
}
