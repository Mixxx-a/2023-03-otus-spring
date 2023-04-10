package ru.sladkov.otus.spring.hw01.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sladkov.otus.spring.hw01.dao.QuestionItemDao;
import ru.sladkov.otus.spring.hw01.service.QuestionsLoader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuestionsLoaderCsvImpl implements QuestionsLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionsLoader.class);

    private final String pathToCsv;

    public QuestionsLoaderCsvImpl(String pathToCsv) {
        this.pathToCsv = pathToCsv;
    }

    @Override
    public List<QuestionItemDao> getQuestions() {
        List<QuestionItemDao> questions = new ArrayList<>();
        try (Scanner scanner = new Scanner(new InputStreamReader(this.getClass().getResourceAsStream(pathToCsv)))) {
            if (!scanner.hasNext()) {
                LOGGER.error("Empty csv was passed! No questions added");
                return questions;
            }
            //Skip first line with meta information
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                List<String> data = Arrays.stream(line.split(",")).toList();
                int correctAnswerIndex = Integer.parseInt(data.get(1)) - 1;
                QuestionItemDao question = new QuestionItemDao(data.get(0), data.subList(2, data.size()),
                        correctAnswerIndex);
                questions.add(question);
            }
        }
        return questions;
    }
}
