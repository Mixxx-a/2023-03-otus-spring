package ru.sladkov.otus.spring.hw06.service.impl;

import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw06.dao.AuthorDao;
import ru.sladkov.otus.spring.hw06.dao.BookDao;
import ru.sladkov.otus.spring.hw06.dao.GenreDao;
import ru.sladkov.otus.spring.hw06.domain.Author;
import ru.sladkov.otus.spring.hw06.domain.Book;
import ru.sladkov.otus.spring.hw06.domain.Genre;
import ru.sladkov.otus.spring.hw06.dto.BookDto;
import ru.sladkov.otus.spring.hw06.exception.NotFoundException;
import ru.sladkov.otus.spring.hw06.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public Book createBook(BookDto bookDto) {
        Long authorId = bookDto.authorId();
        Author author = authorDao.getById(authorId)
                .orElseThrow(() -> new NotFoundException("Create book: Author with id = " + authorId + " is not found"));

        Long genreId = bookDto.genreId();
        Genre genre = genreDao.getById(genreId)
                .orElseThrow(() -> new NotFoundException("Create book: Genre with id = " + genreId + " is not found"));

        Book newBook = new Book(null, bookDto.title(), author, genre);
        return bookDao.create(newBook);
    }

    @Override
    public Book getBook(long id) {
        return bookDao.getById(id)
                .orElseThrow(() -> new NotFoundException("Book with id = " + id + " is not found"));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public void updateBook(BookDto newBookDto) {
        Long bookId = newBookDto.id();
        Book exisitingBook = bookDao.getById(bookId)
                .orElseThrow(() -> new NotFoundException("Update book: Book with id = " + bookId + " is not found"));

        Long newAuthorId = newBookDto.authorId();
        Author newAuthor = authorDao.getById(newAuthorId)
                .orElseThrow(() ->
                        new NotFoundException("Update book: Author with id = " + newAuthorId + " is not found"));

        Long newGenreId = newBookDto.genreId();
        Genre newGenre = genreDao.getById(newGenreId)
                .orElseThrow(() ->
                        new NotFoundException("Update book: Genre with id = " + newGenreId + " is not found"));

        Book newBook = new Book(exisitingBook.id(), newBookDto.title(), newAuthor, newGenre);
        bookDao.update(newBook);
    }

    @Override
    public void deleteBook(long id) {
        bookDao.deleteById(id);
    }
}
