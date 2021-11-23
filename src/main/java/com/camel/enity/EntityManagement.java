package com.camel.enity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public interface EntityManagement {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
}