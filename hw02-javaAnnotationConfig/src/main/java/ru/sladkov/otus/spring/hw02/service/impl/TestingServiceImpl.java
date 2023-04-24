package ru.sladkov.otus.spring.hw02.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw02.exception.PrintException;
import ru.sladkov.otus.spring.hw02.exception.ScanException;
import ru.sladkov.otus.spring.hw02.model.Question;
import ru.sladkov.otus.spring.hw02.model.Student;
import ru.sladkov.otus.spring.hw02.service.IOService;
import ru.sladkov.otus.spring.hw02.service.QuestionDao;
import ru.sladkov.otus.spring.hw02.service.TestingService;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestingService.class);

    private final QuestionDao questionsLoader;

    private final IOService ioService;

    private final int pointsToPass;

    public TestingServiceImpl(QuestionDao questionsLoader, IOService ioService,
                              @Value("${testingservice.points-to-pass}") int points) {
        this.questionsLoader = questionsLoader;
        this.ioService = ioService;
        this.pointsToPass = points;
    }

    @Override
    public void performTesting() {
        try {
            Student student = getStudentInfo();

            List<Question> questions = questionsLoader.getAll();
            ioService.println("Questions are loaded! Let's start testing.");

            for (int i = 0; i < questions.size(); i++) {
                boolean result = askQuestion(i + 1, questions.get(i));
                if (result) {
                    student.addPoint();
                }
            }
            printResult(student);
        } catch (PrintException e) {
            LOGGER.error("Error during printing", e);
        } catch (ScanException e) {
            LOGGER.error("Error during scanning", e);
        }
    }

    @Override
    public void printQuestionsInfo() {
        List<Question> questions = questionsLoader.getAll();

        try {
            for (int i = 0; i < questions.size(); i++) {
                Question questionItem = questions.get(i);
                printQuestion(i + 1, questionItem);
                ioService.println("Correct answer is: " +
                        questionItem.answers().get(questionItem.correctAnswerIndex()));
            }
        } catch (PrintException e) {
            LOGGER.error("Can't print questions info", e);
        }
    }

    private Student getStudentInfo() throws PrintException, ScanException {
        ioService.println("Hello! Please input your name!");
        String name = ioService.scanNext();
        ioService.println("Good! And now please input surname");
        String surname = ioService.scanNext();
        return new Student(name, surname);
    }

    private boolean askQuestion(int numberOfQuestion, Question question) throws PrintException, ScanException {
        printQuestion(numberOfQuestion, question);
        ioService.print("Your answer (input number of correct answer): ");
        int userAnswer = Integer.parseInt(ioService.scanNext());
        return userAnswer == question.correctAnswerIndex() + 1;
    }

    private void printResult(Student student) throws PrintException {
        int studentPoints = student.getPoints();
        ioService.println("Congratulations, "
                + student.getName()
                + " "
                + student.getSurname()
                + "! You got "
                + studentPoints
                + " points!");
        if (studentPoints >= pointsToPass) {
            ioService.print("You have passed the test!!!");
        } else {
            ioService.print("You don't have enough points to pass the test. Try again!");
        }
    }

    private void printQuestion(int numberOfQuestion, Question question) throws PrintException {
        List<String> answers = question.answers();
        ioService.println("Question №" + numberOfQuestion + ":");
        ioService.println(question.question());
        ioService.print("Answers:");
        for (int i = 0; i < answers.size(); i++) {
            int answerNumber = i + 1;
            ioService.print(" (" + answerNumber + ") " + answers.get(i) + ";");
        }
        ioService.println();
    }
}
