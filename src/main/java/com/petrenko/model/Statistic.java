package com.petrenko.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Statistic {

    private long details;
    private long microchips;
    private long fuel;

}
