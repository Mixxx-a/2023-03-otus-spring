package ru.sladkov.otus.spring.hw05.service;

import ru.sladkov.otus.spring.hw05.domain.Author;
import ru.sladkov.otus.spring.hw05.domain.Book;
import ru.sladkov.otus.spring.hw05.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    boolean createBook(long id, String title, long authorId, long genreId);

    Optional<Book> getBook(long id);

    List<Book> getAllBooks();

    boolean updateBook(long id, String newTitle, long newAuthorId, long newGenreId);

    void deleteBook(long id);

    Optional<Author> getAuthorById(long id);

    List<Author> getAllAuthors();

    Optional<Genre> getGenreById(long id);

    List<Genre> getAllGenres();
}
