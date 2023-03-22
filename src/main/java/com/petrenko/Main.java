package com.petrenko;

import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.Detail;
import com.petrenko.robots.ManagerOfAllRobots;
import com.petrenko.service.Service;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        HibernateFactoryUtil.getSessionFactory();

        Service service = Service.getInstance();

        ManagerOfAllRobots[] managerOfAllRobots = new ManagerOfAllRobots[5];

        for (int i = 0; i < 5; i++) {
            managerOfAllRobots[i] = new ManagerOfAllRobots();
            managerOfAllRobots[i].start();
            System.out.println("New detail started creating " + i);
        }

        for (int i = 0; i < 5; i++) {
            managerOfAllRobots[i].join();
            System.out.println("New detail finished creating " + i);
        }

        List<Detail> all = service.getAll();
        LOGGER.info("All details from database:");
        all.forEach(d -> LOGGER.info("{}", d));

    }
}