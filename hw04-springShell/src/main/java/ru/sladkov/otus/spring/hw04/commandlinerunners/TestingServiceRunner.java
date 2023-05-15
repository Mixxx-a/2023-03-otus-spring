package ru.sladkov.otus.spring.hw04.commandlinerunners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.sladkov.otus.spring.hw04.service.TestingService;

@Component
public class TestingServiceRunner implements CommandLineRunner {

    private final TestingService testingService;

    public TestingServiceRunner(TestingService testingService) {
        this.testingService = testingService;
    }

    @Override
    public void run(String... args) {
        testingService.performTesting();
    }
}
