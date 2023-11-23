package ru.sladkov.otus.spring.hw12.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw12.domain.Author;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AuthorRepository should")
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("correctly create and read author")
    @Test
    void shouldCreateReadAuthor() {
        Author newAuthor = authorRepository.save(new Author(null, "newAuthorForename",
                "newAuthorSurname"));

        Optional<Author> optAuthor = authorRepository.findById(newAuthor.getId());
        assertThat(optAuthor.isPresent()).isTrue();
        Author author = optAuthor.get();
        assertThat(author.getId()).isEqualTo(newAuthor.getId());
        assertThat(author.getForename()).isEqualTo(newAuthor.getForename());
        assertThat(author.getSurname()).isEqualTo(newAuthor.getSurname());
    }
}
