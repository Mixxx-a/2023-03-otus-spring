package ru.sladkov.otus.spring.hw09.service;

import ru.sladkov.otus.spring.hw09.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(long id);

    List<Genre> getAll();
}
