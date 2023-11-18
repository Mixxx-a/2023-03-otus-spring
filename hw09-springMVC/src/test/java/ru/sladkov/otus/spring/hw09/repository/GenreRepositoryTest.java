package ru.sladkov.otus.spring.hw09.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw09.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GenreRepository should")
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("correctly create and read genre")
    @Test
    void shouldCreateReadGenre() {
        Genre newGenre = genreRepository.save(new Genre(null, "newGenreName"));

        Optional<Genre> optGenre = genreRepository.findById(newGenre.getId());
        assertThat(optGenre.isPresent()).isTrue();
        Genre genre = optGenre.get();
        assertThat(genre.getId()).isEqualTo(newGenre.getId());
        assertThat(genre.getName()).isEqualTo(newGenre.getName());
    }
}
