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
    private int microchips;
    private int fuel;
    private LocalDateTime start;
    private long seconds;

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return "Detail{" +
                "time start = " + timeFormatter.format(start) +
                "; seconds spent = " + seconds +
                "; id = '" + id + '\'' +
                "; broken microchips = " + microchips +
                "; fuel created = " + fuel +
                '}';
    }
}
