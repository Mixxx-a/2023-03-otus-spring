package ru.sladkov.otus.spring.hw18.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw18.domain.Author;
import ru.sladkov.otus.spring.hw18.domain.Book;
import ru.sladkov.otus.spring.hw18.domain.Genre;
import ru.sladkov.otus.spring.hw18.dto.BookCreateDto;
import ru.sladkov.otus.spring.hw18.dto.BookDto;
import ru.sladkov.otus.spring.hw18.dto.BookUpdateDto;
import ru.sladkov.otus.spring.hw18.exception.NotFoundException;
import ru.sladkov.otus.spring.hw18.repository.AuthorRepository;
import ru.sladkov.otus.spring.hw18.repository.BookRepository;
import ru.sladkov.otus.spring.hw18.repository.CommentRepository;
import ru.sladkov.otus.spring.hw18.repository.GenreRepository;
import ru.sladkov.otus.spring.hw18.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;

    private final BookDto dummyBookDto1 = new BookDto()
            .setId(1L)
            .setTitle("Dummy Title 1")
            .setAuthorId(1L)
            .setAuthorInfo("Author 1")
            .setGenreId(1L)
            .setGenreInfo("Genre 1");

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
    @HystrixCommand(commandKey = "getBook", fallbackMethod = "getDummyBook", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public BookDto getById(long id) {
        Util.sleepRandomly();
        Book savedBook = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id = " + id + " is not found"));
        return BookDto.fromDomainObject(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(commandKey = "getAllBooks", fallbackMethod = "getDummyBooks", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public List<BookDto> getAll() {
        Util.sleepRandomly();
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

    private BookDto getDummyBook(long id) {
        return dummyBookDto1;
    }

    private List<BookDto> getDummyBooks() {
        return List.of(
                dummyBookDto1,
                new BookDto()
                        .setId(2L)
                        .setTitle("Dummy Title 2")
                        .setAuthorId(2L)
                        .setAuthorInfo("Author 2")
                        .setGenreId(2L)
                        .setGenreInfo("Genre 2"),
                new BookDto()
                        .setId(3L)
                        .setTitle("Dummy Title 3")
                        .setAuthorId(2L)
                        .setAuthorInfo("Author 2")
                        .setGenreId(1L)
                        .setGenreInfo("Genre 1")
        );
    }
}
