package ru.sladkov.otus.spring.hw07.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw07.repository.AuthorRepository;
import ru.sladkov.otus.spring.hw07.repository.BookRepository;
import ru.sladkov.otus.spring.hw07.repository.CommentRepository;
import ru.sladkov.otus.spring.hw07.repository.GenreRepository;
import ru.sladkov.otus.spring.hw07.domain.Author;
import ru.sladkov.otus.spring.hw07.domain.Book;
import ru.sladkov.otus.spring.hw07.domain.Genre;
import ru.sladkov.otus.spring.hw07.dto.BookDto;
import ru.sladkov.otus.spring.hw07.exception.NotFoundException;
import ru.sladkov.otus.spring.hw07.service.BookService;

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
    public Book getById(long id) {
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

        exisitingBook.setTitle(newBookDto.title());
        exisitingBook.setAuthor(newAuthor);
        exisitingBook.setGenre(newGenre);
        bookRepository.save(exisitingBook);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteAllByBookId(id);
        bookRepository.deleteById(id);
    }
}
