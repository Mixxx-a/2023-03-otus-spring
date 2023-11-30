package ru.sladkov.otus.spring.hw16.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sladkov.otus.spring.hw16.domain.Genre;
import ru.sladkov.otus.spring.hw16.service.GenreService;

import java.util.List;

@RestController
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/api/genres")
    public List<Genre> getAllGenres() {
        return genreService.getAll();
    }
}
