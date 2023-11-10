package ru.sladkov.otus.spring.hw06.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.sladkov.otus.spring.hw06.dao.impl.AuthorDaoJdbc;
import ru.sladkov.otus.spring.hw06.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AuthorDaoJdbc should")
@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @DisplayName("return existing author by id")
    @Test
    void shouldGetAuthorByIdExisting() {
        Optional<Author> optAuthor = authorDaoJdbc.getById(2);
        assertThat(optAuthor.isPresent()).isTrue();
        Author author = optAuthor.get();
        assertThat(author.id()).isEqualTo(2);
        assertThat(author.forename()).isEqualTo("AuthorForename2");
        assertThat(author.surname()).isEqualTo("AuthorSurname2");
    }

    @DisplayName("return empty optional of non-existing author by id")
    @Test
    void shouldGetAuthorByIdNonExisting() {
        Optional<Author> optAuthor = authorDaoJdbc.getById(123456);
        assertThat(optAuthor.isPresent()).isFalse();
    }

    @DisplayName("return all authors")
    @Test
    void shouldGetAuthors() {
        List<Author> authors = authorDaoJdbc.getAll();
        assertThat(authors).hasSize(3);
    }
}
