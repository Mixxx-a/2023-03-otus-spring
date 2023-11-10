package ru.sladkov.otus.spring.hw05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.sladkov.otus.spring.hw05.dao.impl.GenreDaoJdbc;
import ru.sladkov.otus.spring.hw05.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GenreDaoJdbc")
@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("return existing genre by id")
    @Test
    void shouldGetGenreByIdExisting() {
        Optional<Genre> optGenre = genreDaoJdbc.getById(4);
        assertThat(optGenre.isPresent()).isTrue();
        Genre genre = optGenre.get();
        assertThat(genre.id()).isEqualTo(4);
        assertThat(genre.name()).isEqualTo("Genre4");
    }

    @DisplayName("return empty optional of non-existing genre by id")
    @Test
    void shouldGetGenreByIdNonExisting() {
        Optional<Genre> optGenre = genreDaoJdbc.getById(123456);
        assertThat(optGenre.isPresent()).isFalse();
    }

    @DisplayName("return all genres")
    @Test
    void shouldGetGenres() {
        List<Genre> genres = genreDaoJdbc.getAll();
        assertThat(genres).hasSize(5);
    }
}
