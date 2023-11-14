package ru.sladkov.otus.spring.hw06.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.sladkov.otus.spring.hw06.repository.impl.JPAAuthorRepository;
import ru.sladkov.otus.spring.hw06.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPAAuthorRepository should")
@DataJpaTest
@Import(JPAAuthorRepository.class)
public class JPAAuthorRepositoryTest {

    @Autowired
    private JPAAuthorRepository JPAAuthorRepository;

    @DisplayName("return existing author by id")
    @Test
    void shouldGetAuthorByIdExisting() {
        Optional<Author> optAuthor = JPAAuthorRepository.findById(2);
        assertThat(optAuthor.isPresent()).isTrue();
        Author author = optAuthor.get();
        assertThat(author.getId()).isEqualTo(2);
        assertThat(author.getForename()).isEqualTo("AuthorForename2");
        assertThat(author.getSurname()).isEqualTo("AuthorSurname2");
    }

    @DisplayName("return empty optional of non-existing author by id")
    @Test
    void shouldGetAuthorByIdNonExisting() {
        Optional<Author> optAuthor = JPAAuthorRepository.findById(123456);
        assertThat(optAuthor.isPresent()).isFalse();
    }

    @DisplayName("return all authors")
    @Test
    void shouldGetAuthors() {
        List<Author> authors = JPAAuthorRepository.findAll();
        assertThat(authors).hasSize(3);
    }
}
