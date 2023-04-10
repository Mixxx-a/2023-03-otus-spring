package ru.sladkov.otus.spring.hw01.service.impl;

import ru.sladkov.otus.spring.hw01.dao.QuestionItemDao;
import ru.sladkov.otus.spring.hw01.service.IOService;
import ru.sladkov.otus.spring.hw01.service.QuestionsLoader;
import ru.sladkov.otus.spring.hw01.service.TestingService;

import java.util.List;

public class TestingServiceImpl implements TestingService {
    private final QuestionsLoader questionsLoader;

    private final IOService ioService;

    private List<QuestionItemDao> questions;

    public TestingServiceImpl(QuestionsLoader questionsLoader, IOService ioService) {
        this.questionsLoader = questionsLoader;
        this.ioService = ioService;
    }

    @Override
    public void printQuestionsInfo() {
        if (questions == null) {
            questions = questionsLoader.getQuestions();
        }

        questions.forEach((questionItem -> {
            List<String> answers = questionItem.answers();
            ioService.println("Question: " + questionItem.question());
            ioService.print("Answers: ");
            for (int i = 0; i < answers.size(); i++) {
                int answerNumber = i + 1;
                ioService.print("(" + answerNumber + ") " + answers.get(i) + "; ");
            }
            ioService.println("\nCorrect answer is: " + answers.get(questionItem.correctAnswerIndex()));
        }));
    }
}
