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

    @SneakyThrows
    @Override
    public void run() {

        Repository repository = Repository.getInstance();
        Detail detail;

        LocalDateTime timeStart = LocalDateTime.now();

        LOGGER.info("Before creating new Robot1");
        Robot1 robot1 = new Robot1();
        robot1.setDaemon(true);
        LOGGER.info("Before starting Robot1");
        robot1.start();

        LOGGER.info("Before creating new ManagerOfRobots2And3");
        ManagerOfRobots2And3 robots2And3 = new ManagerOfRobots2And3();
        LOGGER.info("Before starting ManagerOfRobots2And3");
        robots2And3.start();
        LOGGER.info("Before join for ManagerOfRobots2And3");
        robots2And3.join();
        LOGGER.info("Returned from join ManagerOfRobots2And3");

        LOGGER.info("Before creating new Robot4");
        Robot4 robot4 = new Robot4();
        LOGGER.info("Before starting Robot4");
        robot4.start();
        LOGGER.info("Before join for Robot4");
        robot4.join();
        LOGGER.info("Returned from join Robot4");

        LOGGER.info("Before creating new Robot5");
        Robot5 robot5 = new Robot5(robot1);
        LOGGER.info("Before starting Robot5");
        robot5.start();
        LOGGER.info("Before join for Robot5");
        robot5.join();
        LOGGER.info("Returned from join Robot5");

        LOGGER.info("Before disable() for Robot1");
        robot1.disable();
        LOGGER.info("After disable() for Robot1");

        LocalDateTime timeFinish = LocalDateTime.now();

        detail = new Detail();
        detail.setMicrochips(robot4.getBrokenMicrochips());
        detail.setFuel(robot1.getFuelCreated());
        detail.setStart(timeStart);
        long seconds = timeStart.until(timeFinish, ChronoUnit.SECONDS);
        detail.setSeconds(seconds);

        LOGGER.info("New detail has been created {} ", detail);

        repository.save(detail);

    }
}
