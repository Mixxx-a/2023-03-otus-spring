package ru.sladkov.otus.spring.hw05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw05.domain.Author;
import ru.sladkov.otus.spring.hw05.domain.Book;
import ru.sladkov.otus.spring.hw05.domain.Genre;
import ru.sladkov.otus.spring.hw05.service.impl.LibraryServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("LibraryServiceImpl should")
public class LibraryServiceImplTest {

    @Autowired
    private LibraryServiceImpl libraryService;

    @DisplayName("return existing book by id")
    @Test
    void shouldGetBookByIdExisting() {
        Optional<Book> optBook = libraryService.getBook(2);
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        assertThat(book.id()).isEqualTo(2);
        assertThat(book.title()).isEqualTo("Book2");
    }

    @DisplayName("return empty optional of non-existing book by id")
    @Test
    void shouldGetBookByIdNonExisting() {
        Optional<Book> optBook = libraryService.getBook(4);
        assertThat(optBook.isPresent()).isFalse();
    }

    @DisplayName("create new book")
    @Test
    @Transactional
    void shouldCreateBook() {
        Boolean result = libraryService.createBook(4, "Book4", 1, 1);

        assertThat(result).isTrue();
        Optional<Book> optBook = libraryService.getBook(4);
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        assertThat(book.id()).isEqualTo(4);
        assertThat(book.title()).isEqualTo("Book4");
    }

    @DisplayName("not create existing book")
    @Test
    @Transactional
    void shouldNotCreateExistingBook() {
        Boolean result = libraryService.createBook(3, "Book3New", 1, 1);

        assertThat(result).isFalse();
        Optional<Book> optBook = libraryService.getBook(3);
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        assertThat(book.id()).isEqualTo(3);
        assertThat(book.title()).isEqualTo("Book3");
    }

    @DisplayName("not create book with non-existing author")
    @Test
    @Transactional
    void shouldNotCreateBookNonExistingAuthor() {
        Boolean result = libraryService.createBook(4, "Book4", 123, 1);

        assertThat(result).isFalse();
        Optional<Book> optBook = libraryService.getBook(4);
        assertThat(optBook.isPresent()).isFalse();
    }

    @DisplayName("not create book with non-existing genre")
    @Test
    @Transactional
    void shouldNotCreateBookNonExistingGenre() {
        Boolean result = libraryService.createBook(4, "Book4", 1, 123);

        assertThat(result).isFalse();
        Optional<Book> optBook = libraryService.getBook(4);
        assertThat(optBook.isPresent()).isFalse();
    }

    @DisplayName("update book")
    @Test
    @Transactional
    void shouldUpdateBook() {
        Boolean result = libraryService.updateBook(3, "Book3New", 1, 1);

        assertThat(result).isTrue();
        Optional<Book> optBook = libraryService.getBook(3);
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        assertThat(book.id()).isEqualTo(3);
        assertThat(book.title()).isEqualTo("Book3New");
    }

    @DisplayName("not update non-existing book")
    @Test
    @Transactional
    void shouldNotUpdateNonExistingBook() {
        Boolean result = libraryService.updateBook(4, "Book4", 1, 1);

        assertThat(result).isFalse();
        Optional<Book> optBook = libraryService.getBook(4);
        assertThat(optBook.isPresent()).isFalse();
    }

    @DisplayName("not update book with non-existing author")
    @Test
    @Transactional
    void shouldNotUpdateBookNonExistingAuthor() {
        Boolean result = libraryService.updateBook(3, "Book3New", 123, 1);

        assertThat(result).isFalse();
        Optional<Book> optBook = libraryService.getBook(3);
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        assertThat(book.id()).isEqualTo(3);
        assertThat(book.title()).isEqualTo("Book3");
    }

    @DisplayName("not update book with non-existing genre")
    @Test
    @Transactional
    void shouldNotUpdateBookNonExistingGenre() {
        Boolean result = libraryService.createBook(3, "Book3New", 1, 123);

        assertThat(result).isFalse();
        Optional<Book> optBook = libraryService.getBook(3);
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        assertThat(book.id()).isEqualTo(3);
        assertThat(book.title()).isEqualTo("Book3");
    }

    @DisplayName("delete book")
    @Test
    @Transactional
    void shouldDeleteBook() {
        libraryService.deleteBook(1);

        Optional<Book> optBook = libraryService.getBook(1);
        assertThat(optBook.isPresent()).isFalse();
    }

    @DisplayName("return existing author by id")
    @Test
    void shouldGetAuthorByIdExisting() {
        Optional<Author> optAuthor = libraryService.getAuthorById(2);
        assertThat(optAuthor.isPresent()).isTrue();
        Author author = optAuthor.get();
        assertThat(author.id()).isEqualTo(2);
        assertThat(author.forename()).isEqualTo("AuthorForename2");
        assertThat(author.surname()).isEqualTo("AuthorSurname2");
    }

    @DisplayName("return empty optional of non-existing author by id")
    @Test
    void shouldGetAuthorByIdNonExisting() {
        Optional<Author> optAuthor = libraryService.getAuthorById(123456);
        assertThat(optAuthor.isPresent()).isFalse();
    }

    @DisplayName("return all authors")
    @Test
    void shouldGetAuthors() {
        List<Author> authors = libraryService.getAllAuthors();
        assertThat(authors).hasSize(3);
    }

    @DisplayName("return existing genre by id")
    @Test
    void shouldGetGenreByIdExisting() {
        Optional<Genre> optGenre = libraryService.getGenreById(4);
        assertThat(optGenre.isPresent()).isTrue();
        Genre genre = optGenre.get();
        assertThat(genre.id()).isEqualTo(4);
        assertThat(genre.name()).isEqualTo("Genre4");
    }

    @DisplayName("return empty optional of non-existing genre by id")
    @Test
    void shouldGetGenreByIdNonExisting() {
        Optional<Genre> optGenre = libraryService.getGenreById(123456);
        assertThat(optGenre.isPresent()).isFalse();
    }

    @DisplayName("return all genres")
    @Test
    void shouldGetGenres() {
        List<Genre> genres = libraryService.getAllGenres();
        assertThat(genres).hasSize(5);
    }
}
