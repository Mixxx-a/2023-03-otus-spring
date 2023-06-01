package ru.sladkov.otus.spring.hw04.service.impl;

import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw04.configs.TestingServiceConfig;
import ru.sladkov.otus.spring.hw04.model.Question;
import ru.sladkov.otus.spring.hw04.model.Student;
import ru.sladkov.otus.spring.hw04.service.IOService;
import ru.sladkov.otus.spring.hw04.service.LocalizationService;
import ru.sladkov.otus.spring.hw04.service.QuestionDao;
import ru.sladkov.otus.spring.hw04.service.TestingService;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {

    private final TestingServiceConfig testingServiceConfig;

    private final QuestionDao questionDao;

    private final IOService ioService;

    private final LocalizationService localizationService;

    public TestingServiceImpl(TestingServiceConfig testingServiceConfig, QuestionDao questionDao, IOService ioService,
                              LocalizationService localizationService) {
        this.testingServiceConfig = testingServiceConfig;
        this.questionDao = questionDao;
        this.ioService = ioService;
        this.localizationService = localizationService;
    }

    @Override
    public void performTesting(Student student) {
        student.resetPoints();
        List<Question> questions = questionDao.getAll();
        ioService.println(localizationService.getLocalizedMessage("testingservice.questions-loaded"));

        for (int i = 0; i < questions.size(); i++) {
            boolean result = askQuestion(i + 1, questions.get(i));
            if (result) {
                student.addPoint();
            }
        }
        printResult(student);
    }

    @Override
    public void printQuestionsInfo() {
        List<Question> questions = questionDao.getAll();

        for (int i = 0; i < questions.size(); i++) {
            Question questionItem = questions.get(i);
            printQuestion(i + 1, questionItem);
            ioService.println(localizationService.getLocalizedMessage("testingservice.correct-answer-is",
                    questionItem.answers().get(questionItem.correctAnswerIndex())));
        }
    }

    private boolean askQuestion(int numberOfQuestion, Question question) {
        printQuestion(numberOfQuestion, question);
        ioService.print(localizationService.getLocalizedMessage("testingservice.your-answer"));
        int userAnswer = Integer.parseInt(ioService.scanNext());
        ioService.println();
        return userAnswer == question.correctAnswerIndex() + 1;
    }

    private void printResult(Student student) {
        int studentPoints = student.getPoints();
        ioService.println(localizationService.getLocalizedMessage("testingservice.student-points",
                student.getName(), student.getSurname(), studentPoints));
        if (studentPoints >= testingServiceConfig.getPointsToPass()) {
            ioService.print(localizationService.getLocalizedMessage("testingservice.test-passed"));
        } else {
            ioService.print(localizationService.getLocalizedMessage("testingservice.test-failed"));
        }
    }

    private void printQuestion(int numberOfQuestion, Question question) {
        List<String> answers = question.answers();
        ioService.println(localizationService.getLocalizedMessage("testingservice.question-number", numberOfQuestion));
        ioService.println(question.question());
        ioService.print(localizationService.getLocalizedMessage("testingservice.answers"));
        for (int i = 0; i < answers.size(); i++) {
            int answerNumber = i + 1;
            ioService.print(" (" + answerNumber + ") " + answers.get(i) + ";");
        }
        ioService.println();
    }
}
