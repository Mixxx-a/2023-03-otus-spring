package ru.sladkov.otus.spring.hw15.service.impl;

import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw15.domain.Car;
import ru.sladkov.otus.spring.hw15.domain.CarBody;
import ru.sladkov.otus.spring.hw15.domain.CarOrderSpecification;
import ru.sladkov.otus.spring.hw15.service.BodyService;

import static ru.sladkov.otus.spring.hw15.Util.delay;

@Service
public class BodyServiceImpl implements BodyService {
    @Override
    public Car buildBody(CarOrderSpecification carOrderSpecification) {
        delay(4000);
        Car car = new Car(carOrderSpecification);
        car.setBody(new CarBody(carOrderSpecification.carBodyType()));
        return car;
    }
}
