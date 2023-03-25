package com.petrenko.service;

import com.petrenko.model.Statistic;
import com.petrenko.model.Detail;
import com.petrenko.repository.Repository;
import com.petrenko.robots.ManagerOfAllRobots;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


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

    public Statistic getStatisticAllDetails() {
        return repository.getStatisticAllDetails();
    }

    @SneakyThrows
    public void create() {
        new ManagerOfAllRobots().start();
    }

    public List<String> getAllId() {
        return repository.getAllId();
    }


    public Optional<Detail> getById(String id) {
        return repository.getById(id);
    }
}
