package ru.sladkov.otus.spring.hw02.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sladkov.otus.spring.hw02.exception.PrintException;
import ru.sladkov.otus.spring.hw02.model.Question;
import ru.sladkov.otus.spring.hw02.service.IOService;
import ru.sladkov.otus.spring.hw02.service.QuestionDao;
import ru.sladkov.otus.spring.hw02.service.TestingService;

import java.util.List;

public class TestingServiceImpl implements TestingService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestingService.class);

    private final QuestionDao questionsLoader;

    private final IOService ioService;

    public TestingServiceImpl(QuestionDao questionsLoader, IOService ioService) {
        this.questionsLoader = questionsLoader;
        this.ioService = ioService;
    }

    @Override
    public void printQuestionsInfo() {
        List<Question> questions = questionsLoader.getQuestions();

        try {
            for (Question questionItem : questions) {
                List<String> answers = questionItem.answers();
                ioService.println("Question: " + questionItem.question());
                ioService.print("Answers:");
                for (int i = 0; i < answers.size(); i++) {
                    int answerNumber = i + 1;
                    ioService.print(" (" + answerNumber + ") " + answers.get(i) + ";");
                }
                ioService.println("");
                ioService.println("Correct answer is: " + answers.get(questionItem.correctAnswerIndex()));
            }
        } catch (PrintException e) {
            LOGGER.error("Can't print questions info", e);
        }
    }
}
