package ru.sladkov.otus.spring.hw05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sladkov.otus.spring.hw05.domain.Author;
import ru.sladkov.otus.spring.hw05.exception.NotFoundException;
import ru.sladkov.otus.spring.hw05.service.impl.AuthorServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DisplayName("AuthorServiceImpl should")
public class AuthorServiceImplTest {

    @Autowired
    private AuthorServiceImpl authorService;

    @DisplayName("return existing author by id")
    @Test
    void shouldGetAuthorByIdExisting() {
        Author author = authorService.getAuthorById(2);
        assertThat(author.id()).isEqualTo(2);
        assertThat(author.forename()).isEqualTo("AuthorForename2");
        assertThat(author.surname()).isEqualTo("AuthorSurname2");
    }

    @DisplayName("throw NotFoundException of non-existing author by id")
    @Test
    void throwsExceptionGetAuthorByIdNonExisting() {
        assertThatThrownBy(() -> {
            Author author = authorService.getAuthorById(123456);
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("return all authors")
    @Test
    void shouldGetAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        assertThat(authors).hasSize(3);
    }
}
