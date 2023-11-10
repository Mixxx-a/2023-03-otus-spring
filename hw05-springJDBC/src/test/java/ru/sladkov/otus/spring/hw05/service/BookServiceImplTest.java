package ru.sladkov.otus.spring.hw05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw05.domain.Book;
import ru.sladkov.otus.spring.hw05.dto.BookDto;
import ru.sladkov.otus.spring.hw05.exception.NotFoundException;
import ru.sladkov.otus.spring.hw05.service.impl.BookServiceImpl;

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
        Book book = bookService.getBook(2);
        assertThat(book.id()).isEqualTo(2);
        assertThat(book.title()).isEqualTo("Book2");
    }

    @DisplayName("throw NotFoundException of non-existing book by id")
    @Test
    void shouldGetBookByIdNonExisting() {
        assertThatThrownBy(() -> {
            Book book = bookService.getBook(4);
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("create new book")
    @Test
    @Transactional
    void shouldCreateBook() {
        Book newBook = bookService.createBook(new BookDto("Book4", 1L, 1L));

        Book createdBook = bookService.getBook(4);
        assertThat(createdBook.id()).isEqualTo(4);
        assertThat(createdBook.title()).isEqualTo("Book4");
    }

    @DisplayName("not create book with non-existing author and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotCreateBookNonExistingAuthor() {
        assertThatThrownBy(() -> {
            Book newBook = bookService.createBook(new BookDto("Book4", 123L, 1L));
        }).isInstanceOf(NotFoundException.class);

        assertThatThrownBy(() -> {
            Book createdBook = bookService.getBook(4);
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("not create book with non-existing genre and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotCreateBookNonExistingGenre() {
        assertThatThrownBy(() -> {
            Book newBook = bookService.createBook(new BookDto("Book4", 1L, 123L));
        }).isInstanceOf(NotFoundException.class);

        assertThatThrownBy(() -> {
            Book createdBook = bookService.getBook(4);
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("update book")
    @Test
    @Transactional
    void shouldUpdateBook() {
        bookService.updateBook(new BookDto(3L, "Book3New", 1L, 1L));

        Book updatedBook = bookService.getBook(3);
        assertThat(updatedBook.id()).isEqualTo(3);
        assertThat(updatedBook.title()).isEqualTo("Book3New");
    }

    @DisplayName("not update non-existing book and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotUpdateNonExistingBook() {
        assertThatThrownBy(() -> bookService.updateBook(new BookDto(4L, "Book4", 1L, 1L)))
                .isInstanceOf(NotFoundException.class);

        assertThatThrownBy(() -> {
            Book updatedBook = bookService.getBook(4);
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("not update book with non-existing author and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotUpdateBookNonExistingAuthor() {
        assertThatThrownBy(() -> bookService.updateBook(new BookDto(3L, "Book3New", 123L, 1L)))
                .isInstanceOf(NotFoundException.class);

        Book book = bookService.getBook(3);
        assertThat(book.id()).isEqualTo(3);
        assertThat(book.title()).isEqualTo("Book3");
    }

    @DisplayName("not update book with non-existing genre and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotUpdateBookNonExistingGenre() {
        assertThatThrownBy(() -> bookService.updateBook(new BookDto(3L, "Book3New", 1L, 123L)))
                .isInstanceOf(NotFoundException.class);

        Book book = bookService.getBook(3);
        assertThat(book.id()).isEqualTo(3);
        assertThat(book.title()).isEqualTo("Book3");
    }

    @DisplayName("delete book")
    @Test
    @Transactional
    void shouldDeleteBook() {
        bookService.deleteBook(1);

        assertThatThrownBy(() -> {
            Book deletedBook = bookService.getBook(1);
        }).isInstanceOf(NotFoundException.class);
    }
}
