package ru.sladkov.otus.spring.hw15.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.sladkov.otus.spring.hw15.domain.Car;
import ru.sladkov.otus.spring.hw15.domain.CarOrderSpecification;

@MessagingGateway
public interface CarFactory {
    @Gateway(requestChannel = "orderChannel", replyChannel = "carChannel")
    Car assembleCar(CarOrderSpecification carOrderSpecification);
}
