package ru.sladkov.otus.spring.hw15.service.impl;

import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw15.domain.Car;
import ru.sladkov.otus.spring.hw15.domain.Engine;
import ru.sladkov.otus.spring.hw15.service.EngineService;

import static ru.sladkov.otus.spring.hw15.Util.delay;

@Service
public class EngineServiceImpl implements EngineService {
    @Override
    public Car assembleEngine(Car car) {
        delay(5000);
        car.setEngine(new Engine(car.getCarOrderSpecification().horsePower()));
        return car;
    }
}
