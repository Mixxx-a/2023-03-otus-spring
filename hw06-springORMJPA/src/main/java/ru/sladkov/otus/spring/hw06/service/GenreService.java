package ru.sladkov.otus.spring.hw06.service;

import ru.sladkov.otus.spring.hw06.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(long id);

    List<Genre> getAll();
}
