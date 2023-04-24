package ru.sladkov.otus.spring.hw02.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sladkov.otus.spring.hw02.model.Question;
import ru.sladkov.otus.spring.hw02.service.QuestionDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("QuestionDaoCsvImpl should")
public class QuestionDaoCsvImplTest {

    @DisplayName("correctly load questions from csv")
    @Test
    public void shouldCorrectlyLoadQuestions() {
        QuestionDao questionDao = new QuestionDaoCsvImpl("/questions.csv");
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
