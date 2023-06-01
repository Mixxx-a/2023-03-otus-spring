package ru.sladkov.otus.spring.hw04.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.sladkov.otus.spring.hw04.configs.LocaleConfig;
import ru.sladkov.otus.spring.hw04.configs.TestingServiceConfig;
import ru.sladkov.otus.spring.hw04.model.Question;
import ru.sladkov.otus.spring.hw04.model.Student;
import ru.sladkov.otus.spring.hw04.service.IOService;
import ru.sladkov.otus.spring.hw04.service.QuestionDao;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("TestingServiceImpl should")
public class TestingServiceImplTest {
    private final static String STUDENT_NAME = "Name";
    private final static String STUDENT_SURNAME = "Surname";

    @MockBean
    private TestingServiceConfig testingServiceConfig;

    @MockBean
    private LocaleConfig localeConfig;

    @MockBean
    private QuestionDao questionDao;

    @MockBean
    private IOService ioService;

    @Autowired
    private TestingServiceImpl testingService;

    @BeforeEach
    public void beforeEach() {
        when(testingServiceConfig.getPointsToPass()).thenReturn(1);
        when(localeConfig.getLocale()).thenReturn(Locale.ENGLISH);

        Question question = new Question("Test question", List.of("1", "2"), 0);
        List<Question> questions = List.of(question);
        when(questionDao.getAll()).thenReturn(questions);
    }

    @DisplayName("correctly perform testing (passed)")
    @Test
    public void shouldCorrectlyPerformTestingPassed() {
        when(ioService.scanNext()).thenReturn("1");
        Student student = new Student(STUDENT_NAME, STUDENT_SURNAME);
        testingService.performTesting(student);

        assertThat(student.getPoints()).isEqualTo(1);
    }

    @DisplayName("correctly perform testing (not passed)")
    @Test
    public void shouldCorrectlyPerformTestingNotPassed() {
        when(ioService.scanNext()).thenReturn("2");
        Student student = new Student(STUDENT_NAME, STUDENT_SURNAME);
        testingService.performTesting(student);

        assertThat(student.getPoints()).isEqualTo(0);
    }

    @DisplayName("correctly perform testing multiple times")
    @Test
    public void shouldCorrectlyPerformTestingMultipleTimes() {
        when(ioService.scanNext()).thenReturn("1");
        Student student = new Student(STUDENT_NAME, STUDENT_SURNAME);
        testingService.performTesting(student);
        testingService.performTesting(student);
        testingService.performTesting(student);

        assertThat(student.getPoints()).isEqualTo(1);
    }
}
