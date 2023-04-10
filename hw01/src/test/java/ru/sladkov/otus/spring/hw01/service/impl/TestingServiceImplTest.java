package ru.sladkov.otus.spring.hw01.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sladkov.otus.spring.hw01.dao.QuestionItemDao;
import ru.sladkov.otus.spring.hw01.service.IOService;
import ru.sladkov.otus.spring.hw01.service.QuestionsLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TestingServiceImplTest {

    @Mock
    private QuestionsLoader questionsLoader;

    @Mock
    private InputStream inputStream;

    private ByteArrayOutputStream byteArrayOutputStream;

    private OutputStream outputStream;

    private TestingServiceImpl testingService;


    @BeforeEach
    public void beforeEach() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        outputStream = byteArrayOutputStream;
        IOService ioService = new IOServiceStreamsImpl(inputStream, outputStream);
        testingService = new TestingServiceImpl(questionsLoader, ioService);
    }

    @AfterEach
    public void afterEach() throws IOException {
        inputStream.close();
        outputStream.close();
    }

    @Test
    public void testService() {
        QuestionItemDao question = new QuestionItemDao("Test question", List.of("1", "2"), 0);
        List<QuestionItemDao> questions = List.of(question);
        when(questionsLoader.getQuestions()).thenReturn(questions);

        testingService.printQuestionsInfo();
        String result = byteArrayOutputStream.toString();
        assertThat(result).isEqualTo("Question: Test question\nAnswers: (1) 1; (2) 2; \nCorrect answer is: 1\n");
    }

}
