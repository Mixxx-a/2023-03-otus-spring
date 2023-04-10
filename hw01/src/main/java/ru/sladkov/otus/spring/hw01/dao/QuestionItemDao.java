package ru.sladkov.otus.spring.hw01.dao;

import java.util.List;

public record QuestionItemDao(String question, List<String> answers, int correctAnswerIndex) { }
