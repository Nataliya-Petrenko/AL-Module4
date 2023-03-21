package com.petrenko.robots;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class Robots2And3 extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(Robots2And3.class);
    private final WorkPointsOfRobots2And3 workPoints;
    private final CountDownLatch latch;

    public Robots2And3(final WorkPointsOfRobots2And3 workPoints, final CountDownLatch latch) {
        this.workPoints = workPoints;
        this.latch = latch;
    }

    @SneakyThrows
    @Override
    public void run() {
        LOGGER.info("Start working");

        while (workPoints.getCount() < 100) {

            LOGGER.info("Before invoking workPoints.increase()");
            workPoints.increase(); // todo: do I need synchronize here too additional to syn method?
            LOGGER.info("After invoking workPoints.increase() (workPoints = {})", workPoints.getCount());

            if (workPoints.getCount() >= 100) {
                LOGGER.info("If-break-block because workPoints = {} >= 100 to avoid sleeping", workPoints.getCount());
                break;
            }

            LOGGER.info("Before start rebooting");
            TimeUnit.SECONDS.sleep(2);
            LOGGER.info("After finished rebooting");

        }

        LOGGER.info("Before latch.countDown()");
        latch.countDown();

        LOGGER.info("Stop working");

    }

}

