package com.petrenko;

import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.Detail;
import com.petrenko.service.Service;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HibernateFactoryUtil.getSessionFactory();

        Service service = Service.getInstance();

        for (int i = 0; i < 5; i++) {
            service.create();
            System.out.println("New detail created " + i);
        }

        // todo сервіс мабуть теж окремим потоком, щоб наступні рядки відбувалися пілся створення всіх деталей

        List<Detail> all = service.getAll();
        all.forEach(System.out::println);


    }
}