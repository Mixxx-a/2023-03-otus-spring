package ru.sladkov.otus.spring.hw09.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sladkov.otus.spring.hw09.domain.Author;
import ru.sladkov.otus.spring.hw09.domain.Genre;
import ru.sladkov.otus.spring.hw09.dto.BookCreateDto;
import ru.sladkov.otus.spring.hw09.dto.BookDto;
import ru.sladkov.otus.spring.hw09.dto.BookUpdateDto;
import ru.sladkov.otus.spring.hw09.service.AuthorService;
import ru.sladkov.otus.spring.hw09.service.BookService;
import ru.sladkov.otus.spring.hw09.service.GenreService;

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

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    private final List<Author> authors = List.of(
            new Author(1L, "Forename1", "Surname1"),
            new Author(2L, "Forename2", "Surname2")
    );

    private final List<Genre> genres = List.of(
            new Genre(1L, "Genre1"),
            new Genre(2L, "Genre2")
    );

    private final List<BookDto> bookDtos = List.of(
            new BookDto()
                    .setId(1L)
                    .setTitle("Book title 1")
                    .setAuthorId(authors.get(0).getId())
                    .setAuthorInfo(authors.get(0).toString())
                    .setGenreId(genres.get(0).getId())
                    .setGenreInfo(genres.get(0).toString()),
            new BookDto()
                    .setId(2L)
                    .setTitle("Book title 2")
                    .setAuthorId(authors.get(1).getId())
                    .setAuthorInfo(authors.get(1).toString())
                    .setGenreId(genres.get(1).getId())
                    .setGenreInfo(genres.get(1).toString()),
            new BookDto()
                    .setId(3L)
                    .setTitle("Book title 3")
                    .setAuthorId(authors.get(0).getId())
                    .setAuthorInfo(authors.get(0).toString())
                    .setGenreId(genres.get(1).getId())
                    .setGenreInfo(genres.get(1).toString())
    );

    @Test
    @DisplayName("correctly return books page")
    void shouldReturnBooksPage() throws Exception {
        given(bookService.getAll()).willReturn(bookDtos);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(bookDtos.get(0).title())))
                .andExpect(content().string(containsString(bookDtos.get(1).title())))
                .andExpect(content().string(containsString(bookDtos.get(2).title())));

        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("correctly return book details page")
    void shouldReturnBookDetailsPage() throws Exception {
        given(bookService.getById(1L)).willReturn(bookDtos.get(0));

        mvc.perform(get("/book?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(bookDtos.get(0).title())));
    }

    @Test
    @DisplayName("correctly return create book page")
    void shouldReturnCreateBookPage() throws Exception {
        given(authorService.getAll()).willReturn(authors);
        given(genreService.getAll()).willReturn(genres);

        mvc.perform(get("/book/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(authors.get(0).getForename())))
                .andExpect(content().string(containsString(authors.get(0).getSurname())))
                .andExpect(content().string(containsString(authors.get(1).getForename())))
                .andExpect(content().string(containsString(authors.get(1).getSurname())))
                .andExpect(content().string(containsString(genres.get(0).getName())))
                .andExpect(content().string(containsString(genres.get(1).getName())));
    }

    @Test
    @DisplayName("correctly create new book")
    void shouldCreateNewBook() throws Exception {
        BookCreateDto bookCreateDto = new BookCreateDto("New book title", 1L, 1L);

        mvc.perform(post("/book/create?title=New book title&authorId=1&genreId=1"))
                .andExpect(status().is3xxRedirection());
        verify(bookService, times(1)).create(bookCreateDto);
    }

    @Test
    @DisplayName("not create new book when invalid data is passed")
    void shouldNotCreateNewBookInvalid() throws Exception {
        mvc.perform(post("/book/create"))
                .andExpect(status().is4xxClientError());
        mvc.perform(post("/book/create?authorId=1&genreId=1"))
                .andExpect(status().is4xxClientError());
        mvc.perform(post("/book/create?title=New book title&genreId=1"))
                .andExpect(status().is4xxClientError());
        mvc.perform(post("/book/create?title=New book title&authorId=1"))
                .andExpect(status().is4xxClientError());
        verify(bookService, times(0)).create(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("correctly return edit book page")
    void shouldReturnEditBookPage() throws Exception {
        given(bookService.getById(1L)).willReturn(bookDtos.get(0));
        given(authorService.getAll()).willReturn(authors);
        given(genreService.getAll()).willReturn(genres);

        mvc.perform(get("/book/edit?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(bookDtos.get(0).title())))
                .andExpect(content().string(containsString(authors.get(0).getForename())))
                .andExpect(content().string(containsString(authors.get(0).getForename())))
                .andExpect(content().string(containsString(authors.get(0).getSurname())))
                .andExpect(content().string(containsString(authors.get(1).getForename())))
                .andExpect(content().string(containsString(authors.get(1).getSurname())))
                .andExpect(content().string(containsString(genres.get(0).getName())))
                .andExpect(content().string(containsString(genres.get(1).getName())));
    }

    @Test
    @DisplayName("correctly edit book")
    void shouldEditBook() throws Exception {
        BookUpdateDto bookUpdateDto = new BookUpdateDto(1L, "Updated title 1", 2L, 2L);

        mvc.perform(put("/book/edit?id=1&title=Updated title 1&authorId=2&genreId=2"))
                .andExpect(status().is3xxRedirection());
        verify(bookService, times(1)).update(bookUpdateDto);
    }

    @Test
    @DisplayName("not edit book when invalid data is passed")
    void shouldNotEditBookInvalid() throws Exception {
        mvc.perform(post("/book/edit"))
                .andExpect(status().is4xxClientError());
        mvc.perform(post("/book/edit?title=Updated title 1&authorId=2&genreId=2"))
                .andExpect(status().is4xxClientError());
        mvc.perform(post("/book/edit?id=1&authorId=2&genreId=2"))
                .andExpect(status().is4xxClientError());
        mvc.perform(post("/book/edit?id=1&title=Updated title 1&genreId=2"))
                .andExpect(status().is4xxClientError());
        mvc.perform(post("/book/edit?id=1&title=Updated title 1&authorId=2"))
                .andExpect(status().is4xxClientError());
        verify(bookService, times(0)).update(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("correctly delete book")
    void shouldDeleteBook() throws Exception {
        mvc.perform(delete("/book/delete?id=2"))
                .andExpect(status().is3xxRedirection());
        verify(bookService, times(1)).deleteById(2);
    }
}
