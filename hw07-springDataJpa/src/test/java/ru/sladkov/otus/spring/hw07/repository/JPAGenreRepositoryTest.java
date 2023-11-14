package ru.sladkov.otus.spring.hw07.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.sladkov.otus.spring.hw07.domain.Genre;
import ru.sladkov.otus.spring.hw07.repository.impl.JPAGenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPAGenreRepository should")
@DataJpaTest
@Import(JPAGenreRepository.class)
public class JPAGenreRepositoryTest {

    @Autowired
    private JPAGenreRepository JPAGenreRepository;

    @DisplayName("return existing genre by id")
    @Test
    void shouldGetGenreByIdExisting() {
        Optional<Genre> optGenre = JPAGenreRepository.findById(4);
        assertThat(optGenre.isPresent()).isTrue();
        Genre genre = optGenre.get();
        assertThat(genre.getId()).isEqualTo(4);
        assertThat(genre.getName()).isEqualTo("Genre4");
    }

    @DisplayName("return empty optional of non-existing genre by id")
    @Test
    void shouldGetGenreByIdNonExisting() {
        Optional<Genre> optGenre = JPAGenreRepository.findById(123456);
        assertThat(optGenre.isPresent()).isFalse();
    }

    @DisplayName("return all genres")
    @Test
    void shouldGetGenres() {
        List<Genre> genres = JPAGenreRepository.findAll();
        assertThat(genres).hasSize(5);
    }
}
