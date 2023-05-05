package ru.sladkov.otus.spring.hw03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.sladkov.otus.spring.hw03.service.TestingService;

@ComponentScan
@Configuration
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
        TestingService service = ctx.getBean(TestingService.class);
        service.performTesting();
    }
}
