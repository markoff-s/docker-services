package com.da.docker.service2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Counter {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private int counter;

    public Counter() {
    }

    public Counter(int counter) {
        this.counter = counter;
    }

    public long getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }
}
