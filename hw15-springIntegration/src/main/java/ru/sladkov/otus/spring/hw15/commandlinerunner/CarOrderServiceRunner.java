package ru.sladkov.otus.spring.hw15.commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.sladkov.otus.spring.hw15.service.CarOrderService;

@Component
public class CarOrderServiceRunner implements CommandLineRunner {

    private final CarOrderService carOrderService;

    public CarOrderServiceRunner(CarOrderService carOrderService) {
        this.carOrderService = carOrderService;
    }

    @Override
    public void run(String... args) {
        carOrderService.startCarOrders();
    }
}
