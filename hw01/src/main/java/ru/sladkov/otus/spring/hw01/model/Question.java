package ru.sladkov.otus.spring.hw01.model;

import java.util.List;

public record Question(String question, List<String> answers, int correctAnswerIndex) {
}
