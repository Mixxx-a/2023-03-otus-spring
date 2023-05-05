package ru.sladkov.otus.spring.hw03.service;

import ru.sladkov.otus.spring.hw03.model.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAll();
}
