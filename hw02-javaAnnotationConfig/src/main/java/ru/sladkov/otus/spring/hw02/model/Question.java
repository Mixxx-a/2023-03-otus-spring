package ru.sladkov.otus.spring.hw02.model;

import java.util.List;

public record Question(String question, List<String> answers, int correctAnswerIndex) {
}
