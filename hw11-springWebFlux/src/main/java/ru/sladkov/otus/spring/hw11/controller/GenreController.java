package ru.sladkov.otus.spring.hw11.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.sladkov.otus.spring.hw11.domain.Genre;
import ru.sladkov.otus.spring.hw11.repository.GenreRepository;

@SuppressWarnings("unused")
@RestController
public class GenreController {

    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/api/genres")
    public Flux<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
