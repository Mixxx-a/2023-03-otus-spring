package ru.sladkov.otus.spring.hw01.service;

import ru.sladkov.otus.spring.hw01.model.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions();
}
