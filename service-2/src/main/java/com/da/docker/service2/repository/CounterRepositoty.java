package com.da.docker.service2.repository;

import com.da.docker.service2.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepositoty extends JpaRepository<Counter, Long> {
}
