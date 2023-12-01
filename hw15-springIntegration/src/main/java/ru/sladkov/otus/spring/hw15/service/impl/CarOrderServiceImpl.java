package ru.sladkov.otus.spring.hw15.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw15.domain.Car;
import ru.sladkov.otus.spring.hw15.domain.CarBodyType;
import ru.sladkov.otus.spring.hw15.domain.CarOrderSpecification;
import ru.sladkov.otus.spring.hw15.service.CarFactory;
import ru.sladkov.otus.spring.hw15.service.CarOrderService;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static ru.sladkov.otus.spring.hw15.Util.delay;

@Service
public class CarOrderServiceImpl implements CarOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarOrderService.class);

    private final CarFactory carFactory;

    public CarOrderServiceImpl(CarFactory carFactory) {
        this.carFactory = carFactory;
    }

    @Override
    public void startCarOrders() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        for (int i = 0; i < 10; i++) {
            int num = i + 1;
            pool.execute(() -> {
                CarOrderSpecification order = generateCarOrderSpecification();
                LOGGER.info("{}, New order: {}", num, order);

                Car car = carFactory.assembleCar(order);
                LOGGER.info("{}, Ready car: {}", num, car);
            });
            delay(7000);
        }
    }

    private CarOrderSpecification generateCarOrderSpecification() {
        Random random = new Random();
        int horsePower = random.nextInt(100, 500) + 100;
        int carBodyTypeIndex = random.nextInt(2);
        return new CarOrderSpecification(horsePower, CarBodyType.values()[carBodyTypeIndex]);
    }
}
