package ru.sladkov.otus.spring.hw08.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.sladkov.otus.spring.hw08.domain.Author;
import ru.sladkov.otus.spring.hw08.domain.Book;
import ru.sladkov.otus.spring.hw08.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookRepository should")
@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @DisplayName("correctly create and read book")
    @Test
    void shouldCreateReadBook() {
        Author author = mongoTemplate.findById("1", Author.class);
        Genre genre = mongoTemplate.findById("1", Genre.class);
        Book newBook = bookRepository.save(new Book(null, "newBookTitle", author, genre));

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
}
