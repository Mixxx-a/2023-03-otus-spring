package ru.sladkov.otus.spring.hw09.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.sladkov.otus.spring.hw09.domain.Author;
import ru.sladkov.otus.spring.hw09.domain.Book;
import ru.sladkov.otus.spring.hw09.domain.Genre;
import ru.sladkov.otus.spring.hw09.dto.BookDto;
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

    private final List<Book> books = List.of(
            new Book(1L, "Book title 1", authors.get(0), genres.get(0)),
            new Book(2L, "Book title 2", authors.get(1), genres.get(1)),
            new Book(3L, "Book title 3", authors.get(0), genres.get(1))
    );

    @Test
    @DisplayName("correctly return books page")
    void shouldReturnBooksPage() throws Exception {
        given(bookService.getAll()).willReturn(books);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(books.get(0).getTitle())))
                .andExpect(content().string(containsString(books.get(1).getTitle())))
                .andExpect(content().string(containsString(books.get(2).getTitle())));

        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("correctly return book details page")
    void shouldReturnBookDetailsPage() throws Exception {
        given(bookService.getById(1L)).willReturn(books.get(0));

        mvc.perform(get("/book?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(books.get(0).getTitle())));
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
        BookDto newBookDto = new BookDto(null, "New book title", 1L, 1L);

        mvc.perform(post("/book/create?title=New book title&authorId=1&genreId=1"))
                .andExpect(status().is3xxRedirection());
        verify(bookService, times(1)).create(newBookDto);
    }

    @Test
    @DisplayName("correctly return edit book page")
    void shouldReturnEditBookPage() throws Exception {
        given(bookService.getById(1L)).willReturn(books.get(0));
        given(authorService.getAll()).willReturn(authors);
        given(genreService.getAll()).willReturn(genres);

        mvc.perform(get("/book/edit?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(books.get(0).getTitle())))
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
        BookDto bookDto = new BookDto(1L, "Updated title 1", 2L, 2L);

        mvc.perform(put("/book/edit?id=1&title=Updated title 1&authorId=2&genreId=2"))
                .andExpect(status().is3xxRedirection());
        verify(bookService, times(1)).update(bookDto);
    }

    @Test
    @DisplayName("correctly delete book")
    void shouldDeleteBook() throws Exception {
        mvc.perform(delete("/book/delete?id=2"))
                .andExpect(status().is3xxRedirection());
        verify(bookService, times(1)).deleteById(2);
    }
}
