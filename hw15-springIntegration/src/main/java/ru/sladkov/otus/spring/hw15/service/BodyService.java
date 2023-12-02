package ru.sladkov.otus.spring.hw15.service;

import ru.sladkov.otus.spring.hw15.domain.Car;
import ru.sladkov.otus.spring.hw15.domain.CarOrderSpecification;

public interface BodyService {

    Car buildBody(CarOrderSpecification car);
}
