package ru.sladkov.otus.spring.hw04.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sladkov.otus.spring.hw04.configs.LocaleConfig;
import ru.sladkov.otus.spring.hw04.configs.RepositoryConfig;
import ru.sladkov.otus.spring.hw04.model.Question;
import ru.sladkov.otus.spring.hw04.service.QuestionDao;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("QuestionDaoCsvImpl should")
public class QuestionDaoCsvImplTest {

    @MockBean
    private RepositoryConfig repositoryConfig;

    @MockBean
    private LocaleConfig localeConfig;

    @Configuration
    public static class TestConfiguration {
        @Bean
        public QuestionDao questionDao(RepositoryConfig repositoryConfig, LocaleConfig localeConfig) {
            return new QuestionDaoCsvImpl(repositoryConfig, localeConfig);
        }
    }

    @Autowired
    QuestionDao questionDao;

    @BeforeEach
    public void beforeEach() {
        when(repositoryConfig.basePathToCsv()).thenReturn("/questions");
        when(localeConfig.locale()).thenReturn(Locale.ENGLISH);
    }

    @DisplayName("correctly load questions from csv")
    @Test
    public void shouldCorrectlyLoadQuestions() {
        List<Question> questions = questionDao.getAll();

        assertThat(questions.size()).isEqualTo(2);

        Question question1 = questions.get(0);
        assertThat(question1.question()).isEqualTo("Test question1");
        assertThat(question1.correctAnswerIndex()).isEqualTo(0);
        assertThat(question1.answers().size()).isEqualTo(3);

        Question question2 = questions.get(1);
        assertThat(question2.question()).isEqualTo("Test question2");
        assertThat(question2.correctAnswerIndex()).isEqualTo(4);
        assertThat(question2.answers().size()).isEqualTo(15);
    }
}
