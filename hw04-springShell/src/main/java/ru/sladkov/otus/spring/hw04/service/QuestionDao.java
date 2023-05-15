package ru.sladkov.otus.spring.hw04.service;

import ru.sladkov.otus.spring.hw04.model.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAll();
}
