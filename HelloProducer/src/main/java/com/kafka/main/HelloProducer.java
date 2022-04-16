package com.kafka.main;

import com.kafka.config.MyProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kafka.*")
public class HelloProducer {

    public static void main(String[] args) {
        SpringApplication.run(HelloProducer.class, args);
        MyProducer.run();
    }

}
