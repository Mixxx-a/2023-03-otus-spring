package ru.sladkov.otus.spring.hw06.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.sladkov.otus.spring.hw06.domain.Author;
import ru.sladkov.otus.spring.hw06.domain.Book;
import ru.sladkov.otus.spring.hw06.domain.Comment;
import ru.sladkov.otus.spring.hw06.domain.Genre;
import ru.sladkov.otus.spring.hw06.repository.impl.BookRepositoryJPA;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookRepositoryJPA should")
@DataJpaTest
@Import(BookRepositoryJPA.class)
public class BookRepositoryJPATest {

    @Autowired
    private BookRepositoryJPA bookRepositoryJPA;

    @Autowired
    private TestEntityManager em;

    @DisplayName("return existing book by id")
    @Test
    void shouldGetBookByIdExisting() {
        Optional<Book> optBook = bookRepositoryJPA.findById(2);
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        assertThat(book.getId()).isEqualTo(2);
        assertThat(book.getTitle()).isEqualTo("Book2");
    }

    @DisplayName("return empty optional of non-existing book by id")
    @Test
    void shouldGetBookByIdNonExisting() {
        Optional<Book> optBook = bookRepositoryJPA.findById(4);
        assertThat(optBook.isPresent()).isFalse();
    }

    @DisplayName("return all books")
    @Test
    void shouldGetBooks() {
        List<Book> book = bookRepositoryJPA.findAll();
        assertThat(book).hasSize(3);
    }

    @DisplayName("save new book")
    @Test
    void shouldSaveBook() {
        Author author = em.find(Author.class, 1);
        Genre genre = em.find(Genre.class, 1);
        Book newBook = new Book(null, "Book4", author, genre);
        bookRepositoryJPA.save(newBook);

        em.flush();

        Book book = em.find(Book.class, 4);
        assertThat(book.getId()).isEqualTo(4);
        assertThat(book.getTitle()).isEqualTo("Book4");
        assertThat(book.getAuthor().getId()).isEqualTo(author.getId());
        assertThat(book.getGenre().getId()).isEqualTo(genre.getId());
    }

    @DisplayName("update existing book")
    @Test
    void shouldUpdateBook() {
        Author newAuthor = em.find(Author.class, 2);
        Genre newGenre = em.find(Genre.class, 2);
        Book newBook = new Book(3L, "Book3Updated", newAuthor, newGenre);
        bookRepositoryJPA.save(newBook);

        em.flush();

        Book book = em.find(Book.class, 3);
        assertThat(book.getId()).isEqualTo(3);
        assertThat(book.getTitle()).isEqualTo("Book3Updated");
        assertThat(book.getAuthor().getId()).isEqualTo(newAuthor.getId());
        assertThat(book.getGenre().getId()).isEqualTo(newGenre.getId());
    }

    @DisplayName("delete book by id")
    @Test
    void shouldDeleteBookById() {
        bookRepositoryJPA.deleteById(1);

        em.flush();

        Book book = em.find(Book.class, 1);
        assertThat(book).isNull();

        //Check that comments are also deleted
        Comment comment1 = em.find(Comment.class, 1);
        assertThat(comment1).isNull();
        Comment comment2 = em.find(Comment.class, 4);
        assertThat(comment2).isNull();
        Comment comment3 = em.find(Comment.class, 5);
        assertThat(comment3).isNull();
    }
}
