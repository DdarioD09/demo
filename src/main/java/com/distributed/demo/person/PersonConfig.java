package com.distributed.demo.person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

import static java.time.Month.APRIL;
import static java.time.Month.JANUARY;

@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository) {
        return args -> {
            Person mariam = new Person(
                    "Mariam",
                    "Arboleda",
                    LocalDate.of(2000, APRIL, 12)
            );
            Person alex = new Person(
                    "Alex",
                    "Rodriguez",
                    LocalDate.of(2002, JANUARY, 1)
            );
            repository.saveAll(
                    Arrays.asList(mariam, alex)
            );
        };
    }
}
