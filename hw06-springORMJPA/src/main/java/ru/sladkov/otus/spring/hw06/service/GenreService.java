package ru.sladkov.otus.spring.hw06.service;

import ru.sladkov.otus.spring.hw06.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getGenreById(long id);

    List<Genre> getAllGenres();
}
