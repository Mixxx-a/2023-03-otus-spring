package ru.sladkov.otus.spring.hw06.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.sladkov.otus.spring.hw06.dao.impl.BookDaoJdbc;
import ru.sladkov.otus.spring.hw06.domain.Author;
import ru.sladkov.otus.spring.hw06.domain.Book;
import ru.sladkov.otus.spring.hw06.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookDaoJdbc should")
@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @DisplayName("return existing book by id")
    @Test
    void shouldGetBookByIdExisting() {
        Optional<Book> optBook = bookDaoJdbc.getById(2);
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        assertThat(book.id()).isEqualTo(2);
        assertThat(book.title()).isEqualTo("Book2");
    }

    @DisplayName("return empty optional of non-existing book by id")
    @Test
    void shouldGetBookByIdNonExisting() {
        Optional<Book> optBook = bookDaoJdbc.getById(4);
        assertThat(optBook.isPresent()).isFalse();
    }

    @DisplayName("return all books")
    @Test
    void shouldGetBooks() {
        List<Book> book = bookDaoJdbc.getAll();
        assertThat(book).hasSize(3);
    }

    @DisplayName("insert new book")
    @Test
    void shouldInsertBook() {
        Author author = new Author(1, "A1", "A1");
        Genre genre = new Genre(1, "G1");
        Book newBook = new Book(null,"Book4", author, genre);
        bookDaoJdbc.create(newBook);

        Optional<Book> optBook = bookDaoJdbc.getById(4);
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        assertThat(book.id()).isEqualTo(4);
        assertThat(book.title()).isEqualTo("Book4");
    }

    @DisplayName("update existing book")
    @Test
    void shouldUpdateBook() {
        Author author = new Author(1, "A1", "A1");
        Genre genre = new Genre(1, "G1");
        Book newBook = new Book(3L, "Book3Updated", author, genre);
        bookDaoJdbc.update(newBook);

        Optional<Book> optBook = bookDaoJdbc.getById(3);
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        assertThat(book.id()).isEqualTo(3);
        assertThat(book.title()).isEqualTo("Book3Updated");
    }

    @DisplayName("delete book by id")
    @Test
    void shouldDeleteBookById() {
        bookDaoJdbc.deleteById(3);

        Optional<Book> optBook = bookDaoJdbc.getById(3);
        assertThat(optBook.isPresent()).isFalse();
    }
}
