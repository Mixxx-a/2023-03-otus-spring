package ru.sladkov.otus.spring.hw05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sladkov.otus.spring.hw05.domain.Genre;
import ru.sladkov.otus.spring.hw05.exception.NotFoundException;
import ru.sladkov.otus.spring.hw05.service.impl.GenreServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DisplayName("GenreServiceImpl should")
public class GenreServiceImplTest {

    @Autowired
    private GenreServiceImpl genreService;

    @DisplayName("return existing genre by id")
    @Test
    void shouldGetGenreByIdExisting() throws NotFoundException {
        Genre genre = genreService.getGenreById(4);
        assertThat(genre.id()).isEqualTo(4);
        assertThat(genre.name()).isEqualTo("Genre4");
    }

    @DisplayName("throw NotFoundException of non-existing genre by id")
    @Test
    void shouldGetGenreByIdNonExisting() {
        assertThatThrownBy(() -> {
            Genre genre = genreService.getGenreById(123456);
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("return all genres")
    @Test
    void shouldGetGenres() {
        List<Genre> genres = genreService.getAllGenres();
        assertThat(genres).hasSize(5);
    }
}
