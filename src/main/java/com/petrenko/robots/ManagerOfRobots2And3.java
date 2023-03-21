package com.petrenko.robots;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ManagerOfRobots2And3 extends Thread{
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerOfRobots2And3.class);
    private static final int NUMBER_OF_THREADS = 2;

    @SneakyThrows
    @Override
    public void run() {

        final WorkPointsOfRobots2And3 workPoints = new WorkPointsOfRobots2And3();
        final CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);

        LOGGER.info("Before creating newFixedThreadPool");
        ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            LOGGER.info("Before threadPool.execute {}", i);
            threadPool.execute(new Robots2And3(workPoints, latch));
        }

        LOGGER.info("Before latch.await()");
        latch.await();
        LOGGER.info("After latch.await()");

        LOGGER.info("Before shutdown");
        threadPool.shutdown();
        LOGGER.info("After shutdown");

        LOGGER.info("Finish working");

    }
}
