package com.petrenko.robots;

import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@Getter
public class Robot1 extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(Robot1.class);
    private boolean isActive = true;
    private final Fuel fuel = new Fuel();
    public void disable() {
        isActive = false;
    }
    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info("Start working (fuel = {})", fuel.getFuelAfterUsage());

        while (isActive) {
            LOGGER.info("Before invoking fuel.increase()");
            fuel.increase();

            LOGGER.info("Start transporting fuel");
            TimeUnit.SECONDS.sleep(3);
            LOGGER.info("Finished transporting fuel");
        }

    }

    public void usageOfFuel(int usageOfFuel) {
        fuel.usage(usageOfFuel);
    }

    public int getFuelAfterUsage() {
        return fuel.getFuelAfterUsage();
    }

    public int getFuelCreated() {
        return fuel.getFuelCreated();
    }

}
