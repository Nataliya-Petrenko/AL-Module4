package com.petrenko.robots;

import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@Getter
public class Robot1 extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(Robot1.class);
    private final Fuel fuel = new Fuel(); // todo може бути проблема з одночасним додаванням і використанням палива

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info("Start working (fuel = {})", fuel.getFuelAfterUsage());

        while (true) {
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
