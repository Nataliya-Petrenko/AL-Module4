package com.petrenko.robots;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Robot4 extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(Robot4.class);
    private int programmingPoints = 0;
    private int brokenMicrochips = 0;
    private final Random random = new Random();

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info("Start programming (programming points = {})", programmingPoints);

        while (programmingPoints < 100) {

            increaseProgrammingPoints();
            breakingMicrochip();

            if (programmingPoints >= 100) {
                LOGGER.info("If-break-block because programmingPoints = {} >= 100 to avoid sleeping", programmingPoints);
                break;
            }

            LOGGER.info("Start rebooting");
            TimeUnit.SECONDS.sleep(1);
            LOGGER.info("Finished rebooting");
        }

        LOGGER.info("Finished programming (programming points = {})", programmingPoints);
    }

    private void increaseProgrammingPoints() {
        int additionalProgrammingPoints = random.nextInt(25, 35);
        programmingPoints += additionalProgrammingPoints;
        LOGGER.info("Created {} points of programming (total points of programming = {})",
                additionalProgrammingPoints, programmingPoints);
    }

    private void breakingMicrochip() {
        if (random.nextInt(100) <= 30) {
            programmingPoints = 0;
            brokenMicrochips++;
            LOGGER.info("The microchip is broken (brokenMicrochips = {})", brokenMicrochips);
        }
    }

    public int getBrokenMicrochips() {
        return brokenMicrochips;
    }
}
