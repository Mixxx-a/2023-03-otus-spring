package ru.sladkov.otus.spring.hw11.controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sladkov.otus.spring.hw11.dto.BookCreateDto;
import ru.sladkov.otus.spring.hw11.dto.BookDto;
import ru.sladkov.otus.spring.hw11.dto.BookUpdateDto;
import ru.sladkov.otus.spring.hw11.repository.AuthorRepository;
import ru.sladkov.otus.spring.hw11.repository.BookRepository;
import ru.sladkov.otus.spring.hw11.repository.GenreRepository;

import java.time.Duration;

@SuppressWarnings("unused")
@RestController
public class BookController {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository,
                          GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping(value = "/api/books", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .delayElements(Duration.ofMillis(300)) // Only for education purposes
                .map(BookDto::fromDomainObject);
    }

    @GetMapping("/api/books/{id}")
    public Mono<ResponseEntity<BookDto>> getBook(@PathVariable("id") String id) {
        return bookRepository.findById(id)
                .map(BookDto::fromDomainObject)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @PostMapping("/api/books")
    public Mono<BookDto> createBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        return bookRepository.save(bookCreateDto.toDomain(authorRepository, genreRepository))
                .map(BookDto::fromDomainObject);
    }

    @PutMapping("/api/books/{id}")
    public Mono<BookDto> updateBook(@PathVariable("id") String id, @RequestBody @Valid BookUpdateDto bookUpdateDto) {
        return bookRepository.save(bookUpdateDto.toDomain(authorRepository, genreRepository))
                .map(BookDto::fromDomainObject);
    }

    @DeleteMapping("/api/books/{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable("id") String id) {
        return bookRepository.deleteById(id)
                .map(ResponseEntity::ok);
    }
}
