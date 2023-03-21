package com.petrenko.service;

import com.petrenko.model.Detail;
import com.petrenko.repository.Repository;
import com.petrenko.robots.ManagerOfAllRobots;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class Service {
    private static Service instance;
    private final Repository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    private Service(final Repository repository) {
        this.repository = repository;
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service(Repository.getInstance());
            LOGGER.info("Service has been created");
        }
        return instance;
    }

    @SneakyThrows
    public void create() {

        ManagerOfAllRobots managerOfAllRobots = new ManagerOfAllRobots();
        managerOfAllRobots.start();
//        Detail detail = managerOfAllRobots.getDetail();
//        repository.save(detail);
//        return detail;
    }

    public List<Detail> getAll() {
        return repository.getAll();
    }
}
