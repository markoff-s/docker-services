package com.da.docker.service2.configuration;

import com.da.docker.service2.model.Counter;
import com.da.docker.service2.repository.CounterRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private CounterRepositoty counterRepositoty;

    @Override
    public void run(String... args) throws Exception {
        Counter counter = new Counter(7);

        counterRepositoty.save(counter);
    }
}
