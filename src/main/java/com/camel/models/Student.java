package com.camel.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "public", catalog = "testdb")
public class Student {
    private int roll;
    private String name;
    private String subject;

    @Id
    @Column(name = "roll")
    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.camel.models.Student that = (com.camel.models.Student) o;
        return roll == that.roll && Objects.equals(name, that.name) && Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roll, name, subject);
    }
}
