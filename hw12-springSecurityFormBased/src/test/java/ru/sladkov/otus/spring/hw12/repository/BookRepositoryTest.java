package ru.sladkov.otus.spring.hw12.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.sladkov.otus.spring.hw12.domain.Author;
import ru.sladkov.otus.spring.hw12.domain.Book;
import ru.sladkov.otus.spring.hw12.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookRepository should")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("correctly create and read book")
    @Test
    void shouldCreateReadBook() {
        Author author = em.find(Author.class, 1);
        Genre genre = em.find(Genre.class, 1);
        Book newBook = bookRepository.save(new Book(null, "newBookTitle", author, genre));

        em.detach(author);
        em.detach(genre);
        em.detach(newBook);

        Optional<Book> optBook = bookRepository.findById(newBook.getId());
        assertThat(optBook.isPresent()).isTrue();
        Book book = optBook.get();
        //Deep comparison
        assertThat(book.getId()).isEqualTo(newBook.getId());
        assertThat(book.getTitle()).isEqualTo(newBook.getTitle());
        Author bookAuthor = book.getAuthor();
        assertThat(bookAuthor.getId()).isEqualTo(author.getId());
        assertThat(bookAuthor.getForename()).isEqualTo(author.getForename());
        assertThat(bookAuthor.getSurname()).isEqualTo(author.getSurname());
        Genre bookGenre = book.getGenre();
        assertThat(bookGenre.getId()).isEqualTo(genre.getId());
        assertThat(bookGenre.getName()).isEqualTo(genre.getName());
    }

    @DisplayName("return all books")
    @Test
    void shouldGetBooks() {
        List<Book> book = bookRepository.findAll();
        assertThat(book).hasSize(3);
    }
}
