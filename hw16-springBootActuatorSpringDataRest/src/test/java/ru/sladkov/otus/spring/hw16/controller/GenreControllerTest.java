package ru.sladkov.otus.spring.hw16.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sladkov.otus.spring.hw16.domain.Genre;
import ru.sladkov.otus.spring.hw16.service.GenreService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("GenreController should")
@WebMvcTest(GenreController.class)
public class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreService genreService;

    private final List<Genre> genres = List.of(
            new Genre(1L, "Genre1"),
            new Genre(2L, "Genre2")
    );

    @Test
    @DisplayName("correctly return all genres")
    void shouldReturnGenres() throws Exception {
        given(genreService.getAll()).willReturn(genres);

        mvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(genres.get(0).getId().toString())))
                .andExpect(content().string(containsString(genres.get(0).getName())))
                .andExpect(content().string(containsString(genres.get(1).getId().toString())))
                .andExpect(content().string(containsString(genres.get(1).getName())));
    }
}
