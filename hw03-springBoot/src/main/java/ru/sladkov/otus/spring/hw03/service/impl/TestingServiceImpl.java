package ru.sladkov.otus.spring.hw03.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw03.configs.LocaleConfig;
import ru.sladkov.otus.spring.hw03.configs.TestingServiceConfig;
import ru.sladkov.otus.spring.hw03.model.Question;
import ru.sladkov.otus.spring.hw03.model.Student;
import ru.sladkov.otus.spring.hw03.service.IOService;
import ru.sladkov.otus.spring.hw03.service.QuestionDao;
import ru.sladkov.otus.spring.hw03.service.TestingService;

import java.util.Arrays;
import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {

    private final TestingServiceConfig testingServiceConfig;

    private final LocaleConfig localeConfig;

    private final QuestionDao questionsLoader;

    private final IOService ioService;

    private final MessageSource messageSource;

    public TestingServiceImpl(TestingServiceConfig testingServiceConfig, LocaleConfig localeConfig,
                              QuestionDao questionsLoader, IOService ioService, MessageSource messageSource) {
        this.testingServiceConfig = testingServiceConfig;
        this.localeConfig = localeConfig;
        this.questionsLoader = questionsLoader;
        this.ioService = ioService;
        this.messageSource = messageSource;
    }

    @Override
    public void performTesting() {
        Student student = getStudentInfo();

        List<Question> questions = questionsLoader.getAll();
        ioService.println(getLocalizedMessage("testingservice.questions-loaded"));

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
        List<Question> questions = questionsLoader.getAll();

        for (int i = 0; i < questions.size(); i++) {
            Question questionItem = questions.get(i);
            printQuestion(i + 1, questionItem);
            ioService.println(getLocalizedMessage("testingservice.correct-answer-is",
                    questionItem.answers().get(questionItem.correctAnswerIndex())));
        }
    }

    private Student getStudentInfo() {
        ioService.println(getLocalizedMessage("testingservice.ask-student-name"));
        String name = ioService.scanNext();
        ioService.println(getLocalizedMessage("testingservice.ask-student-surname"));
        String surname = ioService.scanNext();
        return new Student(name, surname);
    }

    private boolean askQuestion(int numberOfQuestion, Question question) {
        printQuestion(numberOfQuestion, question);
        ioService.print(getLocalizedMessage("testingservice.your-answer"));
        int userAnswer = Integer.parseInt(ioService.scanNext());
        ioService.println();
        return userAnswer == question.correctAnswerIndex() + 1;
    }

    private void printResult(Student student) {
        int studentPoints = student.getPoints();
        ioService.println(getLocalizedMessage("testingservice.student-points",
                student.getName(), student.getSurname(), studentPoints));
        if (studentPoints >= testingServiceConfig.pointsToPass()) {
            ioService.print(getLocalizedMessage("testingservice.test-passed"));
        } else {
            ioService.print(getLocalizedMessage("testingservice.test-failed"));
        }
    }

    private void printQuestion(int numberOfQuestion, Question question) {
        List<String> answers = question.answers();
        ioService.println(getLocalizedMessage("testingservice.question-number", numberOfQuestion));
        ioService.println(question.question());
        ioService.print(getLocalizedMessage("testingservice.answers"));
        for (int i = 0; i < answers.size(); i++) {
            int answerNumber = i + 1;
            ioService.print(" (" + answerNumber + ") " + answers.get(i) + ";");
        }
        ioService.println();
    }

    private String getLocalizedMessage(String code, Object... args) {
        String[] arguments = Arrays.stream(args)
                .map(String::valueOf)
                .toArray(String[]::new);
        return messageSource.getMessage(code, arguments, localeConfig.locale());
    }
}
