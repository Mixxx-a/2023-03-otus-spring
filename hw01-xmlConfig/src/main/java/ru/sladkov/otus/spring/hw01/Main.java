package ru.sladkov.otus.spring.hw01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sladkov.otus.spring.hw01.service.TestingService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestingService service = ctx.getBean(TestingService.class);
        service.printQuestionsInfo();
    }
}
