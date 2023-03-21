package com.petrenko.repository;

import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.Detail;
import com.petrenko.service.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Repository {
    private static Repository instance;
    private static final Logger LOGGER = LoggerFactory.getLogger(Repository.class);
    private Repository() {
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
            LOGGER.info("Repository has been created");
        }
        return instance;
    }

    public void save(Detail detail) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(detail);
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Detail has been saved to the database {} ", detail);

    }

    public List<Detail> getAll() {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Detail> details = session.createQuery("from Detail", Detail.class)
                .list();
        session.close();
        LOGGER.info("All details have been obtained from the database");
        return details;
    }

    /*
    @Override
    public Optional<Order> getByUuid(String id) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Order order = session.createQuery("from Order as o where o.id = :name", Order.class)
                .setParameter("name", id)
                .uniqueResult();
        session.close();
        return Optional.ofNullable(order);
    }

    @Override
    public void deleteByUuid(String id) {
        getByUuid(id).ifPresent(o -> {
            final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.remove(o);
            session.getTransaction().commit();
            session.close();
        });
    }

     */
}
