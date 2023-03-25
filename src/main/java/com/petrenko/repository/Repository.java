package com.petrenko.repository;

import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.Statistic;
import com.petrenko.model.Detail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

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
        LOGGER.info("--->>> Detail has been saved to the database {} ", detail);

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

    public List<String> getAllId() {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<String> ids = session.createQuery("select id from Detail", String.class)
                .list();
        session.close();
        LOGGER.info("ID all details have been obtained from the database");
        return ids;
    }

    public Statistic getStatisticAllDetails() {
        if (getAll().isEmpty()) {
            return new Statistic(0,0,0);
        }
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Statistic> criteriaQuery = criteriaBuilder.createQuery(Statistic.class);
        Root<Detail> root = criteriaQuery.from(Detail.class);
        Expression<Long> details = criteriaBuilder.count(root.get("id"));
        Expression<Long> microchips = criteriaBuilder.sum(root.get("microchips"));
        Expression<Long> fuel = criteriaBuilder.sum(root.get("fuel"));

        criteriaQuery.multiselect(details, microchips, fuel);

        Statistic statistic = entityManager.createQuery((criteriaQuery)).getSingleResult();
        LOGGER.info("Statistic has been obtained from the database: {}", statistic);

        return statistic;
    }

    public Optional<Detail> getById(String id) {
        final SessionFactory sessionFactory = HibernateFactoryUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Detail detail = session.createQuery("from Detail as d where d.id = :name", Detail.class)
                .setParameter("name", id)
                .uniqueResult();
        session.close();
        return Optional.ofNullable(detail);
    }

}
