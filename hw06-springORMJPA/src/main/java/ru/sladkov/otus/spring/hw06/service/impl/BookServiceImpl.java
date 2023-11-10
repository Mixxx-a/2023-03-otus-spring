package ru.sladkov.otus.spring.hw06.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw06.repository.AuthorRepository;
import ru.sladkov.otus.spring.hw06.repository.BookRepository;
import ru.sladkov.otus.spring.hw06.repository.GenreRepository;
import ru.sladkov.otus.spring.hw06.domain.Author;
import ru.sladkov.otus.spring.hw06.domain.Book;
import ru.sladkov.otus.spring.hw06.domain.Genre;
import ru.sladkov.otus.spring.hw06.dto.BookDto;
import ru.sladkov.otus.spring.hw06.exception.NotFoundException;
import ru.sladkov.otus.spring.hw06.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public Book createBook(BookDto bookDto) {
        Long authorId = bookDto.authorId();
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Create book: Author with id = " + authorId + " is not found"));

        Long genreId = bookDto.genreId();
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException("Create book: Genre with id = " + genreId + " is not found"));

        Book newBook = new Book(null, bookDto.title(), author, genre);
        return bookRepository.save(newBook);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBook(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id = " + id + " is not found"));
        return book.loadLazyFields();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void updateBook(BookDto newBookDto) {
        Long bookId = newBookDto.id();
        Book exisitingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Update book: Book with id = " + bookId + " is not found"));

        Long newAuthorId = newBookDto.authorId();
        Author newAuthor = authorRepository.findById(newAuthorId)
                .orElseThrow(() ->
                        new NotFoundException("Update book: Author with id = " + newAuthorId + " is not found"));

        Long newGenreId = newBookDto.genreId();
        Genre newGenre = genreRepository.findById(newGenreId)
                .orElseThrow(() ->
                        new NotFoundException("Update book: Genre with id = " + newGenreId + " is not found"));

        Book newBook = new Book(exisitingBook.getId(), newBookDto.title(), newAuthor, newGenre);
        bookRepository.save(newBook);
    }

    @Override
    @Transactional
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
