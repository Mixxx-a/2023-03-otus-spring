package ru.sladkov.otus.spring.hw11.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.sladkov.otus.spring.hw11.domain.Author;
import ru.sladkov.otus.spring.hw11.repository.AuthorRepository;

@SuppressWarnings("unused")
@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/api/authors")
    public Flux<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
