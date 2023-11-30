package ru.sladkov.otus.spring.hw13.service;

import ru.sladkov.otus.spring.hw13.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(long id);

    List<Genre> getAll();
}
