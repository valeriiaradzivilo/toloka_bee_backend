package com.diplom.toloka_bee_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication()
@EnableMongoRepositories
@EnableMongoAuditing
@EnableScheduling
public class TolokaBeeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TolokaBeeBackendApplication.class, args);
    }

}