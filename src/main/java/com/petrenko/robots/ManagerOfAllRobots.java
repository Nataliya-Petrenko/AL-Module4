package com.petrenko.robots;

import com.petrenko.model.Detail;
import com.petrenko.repository.Repository;
import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public class ManagerOfAllRobots extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerOfAllRobots.class);

    Repository repository = Repository.getInstance();
    private Detail detail;

    @SneakyThrows
    @Override
    public void run() {

        LocalDateTime timeStart = LocalDateTime.now();

        LOGGER.info("Creating new Robot1");
        Robot1 robot1 = new Robot1();
        robot1.setDaemon(true);
        LOGGER.info("Starting Robot1");
        robot1.start();

        LOGGER.info("Creating new Robots2And3");
        ManagerOfRobots2And3 robots2And3 = new ManagerOfRobots2And3();
        LOGGER.info("Starting Robots2And3");
        robots2And3.start();
        LOGGER.info("Invoking join for Robots2And3");
        robots2And3.join();
        LOGGER.info("Returned from join Robots2And3");

        LOGGER.info("Creating new Robot4");
        Robot4 robot4 = new Robot4();
        LOGGER.info("Starting Robot4");
        robot4.start();
        LOGGER.info("Invoking join for Robot4");
        robot4.join();
        LOGGER.info("Returned from join Robot4");

        LOGGER.info("Creating new Robot5");
        Robot5 robot5 = new Robot5(robot1);
        LOGGER.info("Starting Robot5");
        robot5.start();
        robot5.join();

        LocalDateTime timeFinish = LocalDateTime.now();

        detail = new Detail();
        detail.setBrokenMicrochips(robot4.getBrokenMicrochips());
        detail.setFuelCreated(robot1.getFuelCreated());
        detail.setTimeStart(timeStart);
        long seconds = timeStart.until(timeFinish, ChronoUnit.SECONDS);
        detail.setSecondsSpent(seconds);

        LOGGER.info("New detail has been created {} ", detail);

        repository.save(detail);
    }
}
