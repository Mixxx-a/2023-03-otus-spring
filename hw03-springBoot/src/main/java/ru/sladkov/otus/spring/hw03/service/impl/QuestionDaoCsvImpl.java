package ru.sladkov.otus.spring.hw03.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.sladkov.otus.spring.hw03.service.QuestionDao;
import ru.sladkov.otus.spring.hw03.model.Question;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Repository
public class QuestionDaoCsvImpl implements QuestionDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDao.class);

    private final String pathToCsv;

    public QuestionDaoCsvImpl(@Value("${repository.path-to-csv}") String pathToCsv) {
        this.pathToCsv = pathToCsv;
    }

    @Override
    public List<Question> getAll() {
        List<Question> questions = new ArrayList<>();
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
                Question question = new Question(data.get(0), data.subList(2, data.size()),
                        correctAnswerIndex);
                questions.add(question);
            }
        }
        return questions;
    }
}
