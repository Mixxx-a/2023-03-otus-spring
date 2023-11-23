package ru.sladkov.otus.spring.hw10.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sladkov.otus.spring.hw10.domain.Author;
import ru.sladkov.otus.spring.hw10.service.AuthorService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("AuthorController should")
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    private final List<Author> authors = List.of(
            new Author(1L, "Forename1", "Surname1"),
            new Author(2L, "Forename2", "Surname2")
    );

    @Test
    @DisplayName("correctly return all authors")
    void shouldReturnAuthors() throws Exception {
        given(authorService.getAll()).willReturn(authors);

        mvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(authors.get(0).getId().toString())))
                .andExpect(content().string(containsString(authors.get(0).getForename())))
                .andExpect(content().string(containsString(authors.get(0).getSurname())))
                .andExpect(content().string(containsString(authors.get(1).getId().toString())))
                .andExpect(content().string(containsString(authors.get(1).getForename())))
                .andExpect(content().string(containsString(authors.get(1).getSurname())));
    }
}
