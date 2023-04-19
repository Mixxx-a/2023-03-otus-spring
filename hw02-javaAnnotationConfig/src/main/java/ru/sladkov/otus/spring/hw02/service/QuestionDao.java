package ru.sladkov.otus.spring.hw02.service;

import ru.sladkov.otus.spring.hw02.model.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}
