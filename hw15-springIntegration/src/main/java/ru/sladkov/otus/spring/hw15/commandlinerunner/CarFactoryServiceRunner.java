package ru.sladkov.otus.spring.hw15.commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.sladkov.otus.spring.hw15.service.CarOrderService;

@Component
public class CarFactoryServiceRunner implements CommandLineRunner {

    private final CarOrderService carOrderService;

    public CarFactoryServiceRunner(CarOrderService carOrderService) {
        this.carOrderService = carOrderService;
    }

    @Override
    public void run(String... args) {
        carOrderService.startCarOrders();
    }
}
