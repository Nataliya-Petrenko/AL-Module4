package com.petrenko.robots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class WorkPointsOfRobots2And3 {// todo синхронізувати одночасний доступ до класу ресурсів замість синхронізування методу
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkPointsOfRobots2And3.class);
    private int count = 0;
    private final Random random = new Random();

    public synchronized void increase() {
        int additionalWorkPoints = random.nextInt(10, 20);

        LOGGER.info("Before increase workPoints");
        count += additionalWorkPoints;
        LOGGER.info("After increase workPoints: additionalWorkPoints = {}, workPoints = {}", additionalWorkPoints, count);
    }

    public int getCount() {
        return count;
    }
}
