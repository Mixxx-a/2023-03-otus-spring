package ru.sladkov.otus.spring.hw18.service;

import ru.sladkov.otus.spring.hw18.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(long id);

    List<Genre> getAll();
}
