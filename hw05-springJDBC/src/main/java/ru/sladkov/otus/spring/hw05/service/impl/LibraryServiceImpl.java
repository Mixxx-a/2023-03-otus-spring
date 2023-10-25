package ru.sladkov.otus.spring.hw05.service.impl;

import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw05.dao.AuthorDao;
import ru.sladkov.otus.spring.hw05.dao.BookDao;
import ru.sladkov.otus.spring.hw05.dao.GenreDao;
import ru.sladkov.otus.spring.hw05.domain.Author;
import ru.sladkov.otus.spring.hw05.domain.Book;
import ru.sladkov.otus.spring.hw05.domain.Genre;
import ru.sladkov.otus.spring.hw05.service.LibraryService;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    private final BookDao bookDao;

    public LibraryServiceImpl(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
    }

    @Override
    public boolean createBook(long id, String title, long authorId, long genreId) {
        Optional<Book> existingBook = bookDao.getById(id);
        if (existingBook.isEmpty()) {
            Optional<Author> optionalAuthor = authorDao.getById(authorId);
            Optional<Genre> optionalGenre = genreDao.getById(genreId);
            if (optionalAuthor.isPresent() && optionalGenre.isPresent()) {
                Book newBook = new Book(id, title, optionalAuthor.get(), optionalGenre.get());
                bookDao.insert(newBook);
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Book> getBook(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public boolean updateBook(long id, String newTitle, long newAuthorId, long newGenreId) {
        Optional<Book> optionalBook = bookDao.getById(id);
        if (optionalBook.isPresent()) {
            Optional<Author> optionalAuthor = authorDao.getById(newAuthorId);
            Optional<Genre> optionalGenre = genreDao.getById(newGenreId);
            if (optionalAuthor.isPresent() && optionalGenre.isPresent()) {
                Book newBook = new Book(id, newTitle, optionalAuthor.get(), optionalGenre.get());
                bookDao.update(newBook);
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteBook(long id) {
        bookDao.deleteById(id);
    }

    @Override
    public Optional<Author> getAuthorById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.getAll();
    }

    @Override
    public Optional<Genre> getGenreById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.getAll();
    }
}
