package ru.sladkov.otus.spring.hw05.service;

import ru.sladkov.otus.spring.hw05.domain.Genre;
import ru.sladkov.otus.spring.hw05.exception.NotFoundException;

import java.util.List;

public interface GenreService {
    Genre getGenreById(long id) throws NotFoundException;

    List<Genre> getAllGenres();
}
