package com.petrenko.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
public class Detail {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "broken_microchips")
    private int brokenMicrochips;
    @Column(name = "fuel_created")
    private int fuelCreated;
//    @Column(name = "fuel_used")
//    private int fuelUsed;
    @Column(name = "time_start")
    private LocalDateTime timeStart; // todo improve view in DB
    @Column(name = "seconds_spent")
    private long secondsSpent;

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return "Detail{" +
                "time start = " + timeFormatter.format(timeStart) +
                "; seconds spent = " + secondsSpent +
                "; id = '" + id + '\'' +
                "; broken microchips = " + brokenMicrochips +
                "; fuel created = " + fuelCreated +
                '}';
    }
}
