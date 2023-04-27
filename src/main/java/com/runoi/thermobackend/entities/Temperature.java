package com.runoi.thermobackend.entities;


import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "temperatures")
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "temperature_value", nullable = false)
    private Double temperatureValue;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posting_timestamp", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @Column(name = "temperature_note")
    private String note;

}
