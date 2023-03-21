package com.petrenko.robots;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Robot5 extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(Robot5.class);
    private int finaleWorkPoints = 0;
    private final Robot1 robot1;
    final Random random = new Random();

    public Robot5(Robot1 robot1) {
        this.robot1 = robot1;
    }

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info("Start creating detail (finale work points = {})", finaleWorkPoints);

        while (finaleWorkPoints < 100) {

            usageOfFuel();
            increaseFinaleWorkPoints();

            if (finaleWorkPoints >= 100) {
                LOGGER.info("If-break-block because finaleWorkPoints = {} >= 100 to avoid sleeping", finaleWorkPoints);
                break;
            }

            LOGGER.info("Start rebooting");
            TimeUnit.SECONDS.sleep(1);
            LOGGER.info("Finished rebooting");

        }

        LOGGER.info("Finish programming (finale work points = {})", finaleWorkPoints);
    }

    private void usageOfFuel() {
        int usageOfFuel = random.nextInt(350, 700);

        while (usageOfFuel >= robot1.getFuelAfterUsage()) {
//            LOGGER.info("A total amount gallons of fuel is not enough (we need {} but have {}). " +
//                            "{} invoking Thread.yield()", usageOfFuel, robot1.getFuelAfterUsage(),
//                    Thread.currentThread().getName());
            Thread.yield();
        }

        robot1.usageOfFuel(usageOfFuel);
    }

    private void increaseFinaleWorkPoints() {
        int additionalFinaleWorkPoints = 10;
        finaleWorkPoints += additionalFinaleWorkPoints;
        LOGGER.info("{} points of programming done (total points of programming = {})",
                additionalFinaleWorkPoints, finaleWorkPoints);
    }

}
