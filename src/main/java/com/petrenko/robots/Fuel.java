package com.petrenko.robots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Fuel {
    private static final Logger LOGGER = LoggerFactory.getLogger(Fuel.class);
    private int fuelCreated;
    private int fuelAfterUsage;

    private final Random random = new Random();

    public synchronized void increase() {
        int additionalFuel = random.nextInt(500, 1000);

        LOGGER.info("Before increase fuel");
        fuelAfterUsage += additionalFuel;
        fuelCreated += additionalFuel;
        LOGGER.info("Created {} gallons of fuel (total fuel after usage = {})", additionalFuel, fuelAfterUsage);
    }

    public synchronized void usage(int usageOfFuel) {
        if (usageOfFuel >= 0 && usageOfFuel <= fuelAfterUsage) {
            fuelAfterUsage -= usageOfFuel;
            LOGGER.info("{} gallons of fuel is used (total fuel after usage = {})", usageOfFuel, fuelAfterUsage);
        }
    }

    public int getFuelAfterUsage() {
        return fuelAfterUsage;
    }

    public int getFuelCreated() {
        return fuelCreated;
    }
}
