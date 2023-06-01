package ru.sladkov.otus.spring.hw04.service;

import ru.sladkov.otus.spring.hw04.model.Student;

public interface TestingService {
    void performTesting(Student student);

    void printQuestionsInfo();
}
