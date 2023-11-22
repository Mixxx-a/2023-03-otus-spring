package ru.sladkov.otus.spring.hw08.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw08.repository.AuthorRepository;
import ru.sladkov.otus.spring.hw08.repository.BookRepository;
import ru.sladkov.otus.spring.hw08.repository.CommentRepository;
import ru.sladkov.otus.spring.hw08.repository.GenreRepository;
import ru.sladkov.otus.spring.hw08.domain.Author;
import ru.sladkov.otus.spring.hw08.domain.Book;
import ru.sladkov.otus.spring.hw08.domain.Genre;
import ru.sladkov.otus.spring.hw08.dto.BookDto;
import ru.sladkov.otus.spring.hw08.exception.NotFoundException;
import ru.sladkov.otus.spring.hw08.service.BookService;

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
    public Book create(BookDto bookDto) {
        String authorId = bookDto.authorId();
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException("Create book: Author with id = " + authorId + " is not found"));

        String genreId = bookDto.genreId();
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException("Create book: Genre with id = " + genreId + " is not found"));

        Book newBook = new Book(null, bookDto.title(), author, genre);
        return bookRepository.save(newBook);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id = " + id + " is not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void update(BookDto newBookDto) {
        String bookId = newBookDto.id();
        Book exisitingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Update book: Book with id = " + bookId + " is not found"));

        String newAuthorId = newBookDto.authorId();
        Author newAuthor = authorRepository.findById(newAuthorId)
                .orElseThrow(() ->
                        new NotFoundException("Update book: Author with id = " + newAuthorId + " is not found"));

        String newGenreId = newBookDto.genreId();
        Genre newGenre = genreRepository.findById(newGenreId)
                .orElseThrow(() ->
                        new NotFoundException("Update book: Genre with id = " + newGenreId + " is not found"));

        exisitingBook.setTitle(newBookDto.title());
        exisitingBook.setAuthor(newAuthor);
        exisitingBook.setGenre(newGenre);
        bookRepository.save(exisitingBook);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        commentRepository.deleteAllByBookId(id);
        bookRepository.deleteById(id);
    }
}
