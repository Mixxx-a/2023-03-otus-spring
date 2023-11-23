package ru.sladkov.otus.spring.hw10.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw10.dto.BookCreateDto;
import ru.sladkov.otus.spring.hw10.dto.BookUpdateDto;
import ru.sladkov.otus.spring.hw10.repository.AuthorRepository;
import ru.sladkov.otus.spring.hw10.repository.BookRepository;
import ru.sladkov.otus.spring.hw10.repository.CommentRepository;
import ru.sladkov.otus.spring.hw10.repository.GenreRepository;
import ru.sladkov.otus.spring.hw10.domain.Author;
import ru.sladkov.otus.spring.hw10.domain.Book;
import ru.sladkov.otus.spring.hw10.domain.Genre;
import ru.sladkov.otus.spring.hw10.dto.BookDto;
import ru.sladkov.otus.spring.hw10.exception.NotFoundException;
import ru.sladkov.otus.spring.hw10.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public BookDto create(BookCreateDto bookCreateDto) {
        Long authorId = bookCreateDto.authorId();
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Create book: Author with id = " + authorId + " is not found"));

        Long genreId = bookCreateDto.genreId();
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException("Create book: Genre with id = " + genreId + " is not found"));

        Book newBook = new Book(null, bookCreateDto.title(), author, genre);
        Book savedBook = bookRepository.save(newBook);
        return BookDto.fromDomainObject(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getById(long id) {
        Book savedBook = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id = " + id + " is not found"));
        return BookDto.fromDomainObject(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookDto::fromDomainObject)
                .toList();
    }

    @Override
    @Transactional
    public BookDto update(BookUpdateDto bookUpdateDto) {
        Long bookId = bookUpdateDto.id();
        Book exisitingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Update book: Book with id = " + bookId + " is not found"));

        Long newAuthorId = bookUpdateDto.authorId();
        Author newAuthor = authorRepository.findById(newAuthorId)
                .orElseThrow(() ->
                        new NotFoundException("Update book: Author with id = " + newAuthorId + " is not found"));

        Long newGenreId = bookUpdateDto.genreId();
        Genre newGenre = genreRepository.findById(newGenreId)
                .orElseThrow(() ->
                        new NotFoundException("Update book: Genre with id = " + newGenreId + " is not found"));

        exisitingBook.setTitle(bookUpdateDto.title());
        exisitingBook.setAuthor(newAuthor);
        exisitingBook.setGenre(newGenre);

        bookRepository.save(exisitingBook);
        return BookDto.fromDomainObject(exisitingBook);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteAllByBookId(id);
        bookRepository.deleteById(id);
    }
}
