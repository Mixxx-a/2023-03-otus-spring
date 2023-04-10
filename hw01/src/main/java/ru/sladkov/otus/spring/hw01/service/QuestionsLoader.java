package ru.sladkov.otus.spring.hw01.service;

import ru.sladkov.otus.spring.hw01.dao.QuestionItemDao;

import java.util.List;

public interface QuestionsLoader {
    List<QuestionItemDao> getQuestions();
}
