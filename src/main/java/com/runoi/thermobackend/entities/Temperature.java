package com.runoi.thermobackend.entities;


import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "temperatures")
public class Temperature {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "temperature_value", nullable = false)
    private Double temperatureValue;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posting_timestamp", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @Column(name = "temperature_note")
    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(Double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", temperatureValue=" + temperatureValue +
                ", dateTime=" + dateTime +
                ", note='" + note + '\'' +
                '}';
    }
}
