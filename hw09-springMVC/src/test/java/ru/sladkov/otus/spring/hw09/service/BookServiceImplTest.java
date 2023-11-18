package ru.sladkov.otus.spring.hw09.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw09.domain.Book;
import ru.sladkov.otus.spring.hw09.dto.BookDto;
import ru.sladkov.otus.spring.hw09.exception.NotFoundException;
import ru.sladkov.otus.spring.hw09.service.impl.BookServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DisplayName("BookServiceImpl should")
public class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @DisplayName("return existing book by id")
    @Test
    void shouldGetBookByIdExisting() {
        Book book = bookService.getById(2);
        assertThat(book.getId()).isEqualTo(2);
        assertThat(book.getTitle()).isEqualTo("Book2");
    }

    @DisplayName("throw NotFoundException of non-existing book by id")
    @Test
    void shouldGetBookByIdNonExisting() {
        assertThatThrownBy(() -> {
            Book book = bookService.getById(4);
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("create new book")
    @Test
    @Transactional
    void shouldCreateBook() {
        Book newBook = bookService.create(new BookDto("Book4", 1L, 1L));

        Book createdBook = bookService.getById(4);
        assertThat(createdBook.getId()).isEqualTo(4);
        assertThat(createdBook.getTitle()).isEqualTo("Book4");
    }

    @DisplayName("not create book with non-existing author and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotCreateBookNonExistingAuthor() {
        assertThatThrownBy(() -> {
            Book newBook = bookService.create(new BookDto("Book4", 123L, 1L));
        }).isInstanceOf(NotFoundException.class);

        assertThatThrownBy(() -> {
            Book createdBook = bookService.getById(4);
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("not create book with non-existing genre and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotCreateBookNonExistingGenre() {
        assertThatThrownBy(() -> {
            Book newBook = bookService.create(new BookDto("Book4", 1L, 123L));
        }).isInstanceOf(NotFoundException.class);

        assertThatThrownBy(() -> {
            Book createdBook = bookService.getById(4);
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("update book")
    @Test
    @Transactional
    void shouldUpdateBook() {
        bookService.update(new BookDto(3L, "Book3New", 1L, 1L));

        Book updatedBook = bookService.getById(3);
        assertThat(updatedBook.getId()).isEqualTo(3);
        assertThat(updatedBook.getTitle()).isEqualTo("Book3New");
    }

    @DisplayName("not update non-existing book and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotUpdateNonExistingBook() {
        assertThatThrownBy(() -> bookService.update(new BookDto(4L, "Book4", 1L, 1L)))
                .isInstanceOf(NotFoundException.class);

        assertThatThrownBy(() -> {
            Book updatedBook = bookService.getById(4);
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("not update book with non-existing author and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotUpdateBookNonExistingAuthor() {
        assertThatThrownBy(() -> bookService.update(new BookDto(3L, "Book3New", 123L, 1L)))
                .isInstanceOf(NotFoundException.class);

        Book book = bookService.getById(3);
        assertThat(book.getId()).isEqualTo(3);
        assertThat(book.getTitle()).isEqualTo("Book3");
    }

    @DisplayName("not update book with non-existing genre and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotUpdateBookNonExistingGenre() {
        assertThatThrownBy(() -> bookService.update(new BookDto(3L, "Book3New", 1L, 123L)))
                .isInstanceOf(NotFoundException.class);

        Book book = bookService.getById(3);
        assertThat(book.getId()).isEqualTo(3);
        assertThat(book.getTitle()).isEqualTo("Book3");
    }

    @DisplayName("delete book")
    @Test
    @Transactional
    void shouldDeleteBook() {
        bookService.deleteById(1);

        assertThatThrownBy(() -> {
            Book deletedBook = bookService.getById(1);
        }).isInstanceOf(NotFoundException.class);
    }
}
