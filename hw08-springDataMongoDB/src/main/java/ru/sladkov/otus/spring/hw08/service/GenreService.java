package ru.sladkov.otus.spring.hw08.service;

import ru.sladkov.otus.spring.hw08.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(long id);

    List<Genre> getAll();
}
