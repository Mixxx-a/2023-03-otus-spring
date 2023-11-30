package ru.sladkov.otus.spring.hw16.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.sladkov.otus.spring.hw16.dto.BookCreateDto;
import ru.sladkov.otus.spring.hw16.dto.BookDto;
import ru.sladkov.otus.spring.hw16.dto.BookUpdateDto;
import ru.sladkov.otus.spring.hw16.service.BookService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("BookController should")
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    private final List<BookDto> bookDtos = List.of(
            new BookDto()
                    .setId(1L)
                    .setTitle("Book title 1")
                    .setAuthorId(1L)
                    .setAuthorInfo("Author 1")
                    .setGenreId(1L)
                    .setGenreInfo("Genre 1"),
            new BookDto()
                    .setId(2L)
                    .setTitle("Book title 2")
                    .setAuthorId(2L)
                    .setAuthorInfo("Author 2")
                    .setGenreId(2L)
                    .setGenreInfo("Genre 2"),
            new BookDto()
                    .setId(3L)
                    .setTitle("Book title 3")
                    .setAuthorId(1L)
                    .setAuthorInfo("Author 1")
                    .setGenreId(2L)
                    .setGenreInfo("Genre 2")
    );

    @Test
    @DisplayName("correctly return all books")
    void shouldReturnBooks() throws Exception {
        given(bookService.getAll()).willReturn(bookDtos);

        mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(bookDtos.get(0).getId().toString())))
                .andExpect(content().string(containsString(bookDtos.get(0).getTitle())))
                .andExpect(content().string(containsString(bookDtos.get(1).getId().toString())))
                .andExpect(content().string(containsString(bookDtos.get(1).getTitle())))
                .andExpect(content().string(containsString(bookDtos.get(2).getId().toString())))
                .andExpect(content().string(containsString(bookDtos.get(2).getTitle())));
    }

    @Test
    @DisplayName("correctly return selected book")
    void shouldReturnBook() throws Exception {
        given(bookService.getById(2)).willReturn(bookDtos.get(1));

        mvc.perform(get("/api/books/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(bookDtos.get(1).getId().toString())))
                .andExpect(content().string(containsString(bookDtos.get(1).getTitle())));
    }

    @Test
    @DisplayName("correctly create book")
    void shouldCreateBook() throws Exception {
        BookCreateDto bookCreateDto = new BookCreateDto("New book title", 2L, 2L);
        BookDto createdBookDto = new BookDto()
                .setId(4L)
                .setTitle("New book title")
                .setAuthorId(1L)
                .setAuthorInfo("Author 1")
                .setGenreId(1L)
                .setGenreInfo("Genre 1");
        given(bookService.create(bookCreateDto)).willReturn(createdBookDto);

        mvc.perform(post("/api/books")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New book title\",\"authorId\":\"2\",\"genreId\":\"2\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(createdBookDto.getId().toString())))
                .andExpect(content().string(containsString(createdBookDto.getTitle())));
        verify(bookService, times(1)).create(bookCreateDto);
    }

    @Test
    @DisplayName("correctly update selected book")
    void shouldUpdateBook() throws Exception {
        BookUpdateDto bookUpdateDto = new BookUpdateDto(1L, "Updated title", 2L, 2L);
        BookDto updatedBookDto = new BookDto()
                .setId(1L)
                .setTitle("Updated title")
                .setAuthorId(2L)
                .setAuthorInfo("Author 2")
                .setGenreId(2L)
                .setGenreInfo("Genre 2");
        given(bookService.update(bookUpdateDto)).willReturn(updatedBookDto);

        mvc.perform(put("/api/books/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"title\":\"Updated title\",\"authorId\":\"2\",\"genreId\":\"2\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updatedBookDto.getId().toString())))
                .andExpect(content().string(containsString(updatedBookDto.getTitle())));
        verify(bookService, times(1)).update(bookUpdateDto);
    }

    @Test
    @DisplayName("correctly delete book")
    void shouldDeleteBook() throws Exception {
        mvc.perform(delete("/api/books/2"))
                .andExpect(status().isOk());
        verify(bookService, times(1)).deleteById(2);
    }
}
