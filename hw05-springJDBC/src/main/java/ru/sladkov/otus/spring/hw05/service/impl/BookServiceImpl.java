package ru.sladkov.otus.spring.hw05.service.impl;

import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw05.dao.BookDao;
import ru.sladkov.otus.spring.hw05.domain.Author;
import ru.sladkov.otus.spring.hw05.domain.Book;
import ru.sladkov.otus.spring.hw05.domain.Genre;
import ru.sladkov.otus.spring.hw05.dto.BookDTO;
import ru.sladkov.otus.spring.hw05.exception.NotFoundException;
import ru.sladkov.otus.spring.hw05.service.AuthorService;
import ru.sladkov.otus.spring.hw05.service.BookService;
import ru.sladkov.otus.spring.hw05.service.GenreService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookServiceImpl(BookDao bookDao, AuthorService authorService, GenreService genreService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public Book createBook(BookDTO bookDTO) throws NotFoundException {
        Author author = authorService.getAuthorById(bookDTO.authorId());
        Genre genre = genreService.getGenreById(bookDTO.genreId());
        Book newBook = new Book(null, bookDTO.title(), author, genre);
        return bookDao.create(newBook);
    }

    @Override
    public Book getBook(long id) throws NotFoundException {
        return bookDao.getById(id).orElseThrow(() -> new NotFoundException("Book with id = " + id + " is not found"));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public void updateBook(BookDTO newBookDTO) throws NotFoundException {
        Book exisitingBook = this.getBook(newBookDTO.id());
        Author newAuthor = authorService.getAuthorById(newBookDTO.authorId());
        Genre newGenre = genreService.getGenreById(newBookDTO.genreId());
        Book newBook = new Book(exisitingBook.id(), newBookDTO.title(), newAuthor, newGenre);
        bookDao.update(newBook);
    }

    @Override
    public void deleteBook(long id) {
        bookDao.deleteById(id);
    }
}
