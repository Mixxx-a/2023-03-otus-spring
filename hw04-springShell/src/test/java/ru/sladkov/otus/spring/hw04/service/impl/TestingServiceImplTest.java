package ru.sladkov.otus.spring.hw04.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.sladkov.otus.spring.hw04.configs.LocaleConfig;
import ru.sladkov.otus.spring.hw04.configs.TestingServiceConfig;
import ru.sladkov.otus.spring.hw04.model.Question;
import ru.sladkov.otus.spring.hw04.service.IOService;
import ru.sladkov.otus.spring.hw04.service.QuestionDao;

import java.io.*;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("TestingServiceImpl should")
public class TestingServiceImplTest {
    private final static String PRINTED_QUESTION = "Question №1:"
            + System.lineSeparator()
            + "Test question"
            + System.lineSeparator()
            + "Answers: (1) 1; (2) 2;"
            + System.lineSeparator();
    private final static String PRINTED_GREETINGS = "Hello! Please input your name!"
            + System.lineSeparator()
            + "Good! And now please input surname"
            + System.lineSeparator()
            + "Questions are loaded! Let's start testing."
            + System.lineSeparator();

    private final static String PRINTED_ANSWER_PROMPT = "Your answer (input number of correct answer): ";
    private final static String PRINTED_CONGRATULATIONS = "Congratulations, Name Surname! ";
    private final static String PRINTED_TEXT_PASSED = "You have passed the test!!!";
    private final static String PRINTED_TEXT_NOT_PASSED = "You don't have enough points to pass the test. Try again!";

    private final TestingServiceConfig testingServiceConfig = new TestingServiceConfig(1);

    private final LocaleConfig localeConfig = new LocaleConfig(Locale.ENGLISH);

    private final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    @Mock
    private QuestionDao questionsLoader;

    private InputStream inputStream;

    private ByteArrayOutputStream byteArrayOutputStream;

    private OutputStream outputStream;

    private TestingServiceImpl testingService;


    @BeforeEach
    public void beforeEach() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        outputStream = byteArrayOutputStream;

        Question question = new Question("Test question", List.of("1", "2"), 0);
        List<Question> questions = List.of(question);
        when(questionsLoader.getAll()).thenReturn(questions);

        messageSource.setBasenames("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
    }

    @AfterEach
    public void afterEach() throws IOException {
        inputStream.close();
        outputStream.close();
    }

    @DisplayName("correctly print questions info")
    @Test
    public void shouldCorrectlyPrintQuestionsInfo() {
        inputStream = Mockito.mock();
        IOService ioService = new IOServiceStreamsImpl(inputStream, outputStream);
        testingService = new TestingServiceImpl(testingServiceConfig, localeConfig, questionsLoader, ioService, messageSource);

        testingService.printQuestionsInfo();

        String result = byteArrayOutputStream.toString();
        String expected = PRINTED_QUESTION
                + "Correct answer is: 1"
                + System.lineSeparator();
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("correctly perform testing (passed)")
    @Test
    public void shouldCorrectlyPerformTestingPassed() {
        String input = "Name\nSurname\n1";
        inputStream = new ByteArrayInputStream(input.getBytes());
        IOService ioService = new IOServiceStreamsImpl(inputStream, outputStream);
        testingService = new TestingServiceImpl(testingServiceConfig, localeConfig, questionsLoader, ioService, messageSource);

        testingService.performTesting();

        String result = byteArrayOutputStream.toString();
        String expected = PRINTED_GREETINGS
                + PRINTED_QUESTION
                + PRINTED_ANSWER_PROMPT
                + System.lineSeparator()
                + PRINTED_CONGRATULATIONS
                + "You got 1 points!"
                + System.lineSeparator()
                + PRINTED_TEXT_PASSED;
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("correctly perform testing (not passed)")
    @Test
    public void shouldCorrectlyPerformTestingNotPassed() {
        String input = "Name\nSurname\n0";
        inputStream = new ByteArrayInputStream(input.getBytes());
        IOService ioService = new IOServiceStreamsImpl(inputStream, outputStream);
        testingService = new TestingServiceImpl(testingServiceConfig, localeConfig, questionsLoader, ioService, messageSource);

        testingService.performTesting();

        String result = byteArrayOutputStream.toString();
        String expected = PRINTED_GREETINGS
                + PRINTED_QUESTION
                + PRINTED_ANSWER_PROMPT
                + System.lineSeparator()
                + PRINTED_CONGRATULATIONS
                + "You got 0 points!"
                + System.lineSeparator()
                + PRINTED_TEXT_NOT_PASSED;
        assertThat(result).isEqualTo(expected);
    }

}
