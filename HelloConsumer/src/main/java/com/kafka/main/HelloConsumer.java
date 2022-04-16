package com.kafka.main;

import com.kafka.config.MyConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kafka.*")
public class HelloConsumer {

    public static void main(String[] args) {
        SpringApplication.run(HelloConsumer.class, args);
        MyConsumer.run();
    }

}
