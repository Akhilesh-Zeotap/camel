package com.camel.processor;

import com.camel.enity.EntityManagement;
import com.camel.models.Student;
import org.apache.camel.Exchange;

import javax.persistence.EntityManager;

public class StudentService {
    EntityManager entityManager = EntityManagement.entityManager;

    public void getAll(Exchange exchange) {
        entityManager.getTransaction().begin();
        exchange.getIn().setBody(entityManager.createQuery("select s from Student s", Student.class).getResultList());
        entityManager.getTransaction().commit();
    }

    public void getOne(Exchange exchange) {
        int id = Integer.parseInt(exchange.getIn().getHeader("id").toString());
        entityManager.getTransaction().begin();
        exchange.getIn().setBody(entityManager.find(Student.class, id));
        entityManager.getTransaction().commit();
    }

    public void insertOrUpdateOne(Exchange exchange) {
        Student s = exchange.getIn().getBody(Student.class);
        entityManager.getTransaction().begin();
        entityManager.merge(s);
        entityManager.getTransaction().commit();
    }

}
