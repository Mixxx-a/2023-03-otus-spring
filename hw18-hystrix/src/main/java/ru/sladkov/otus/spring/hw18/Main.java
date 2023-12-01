package ru.sladkov.otus.spring.hw18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
