package ru.sladkov.otus.spring.hw11.service;

import ru.sladkov.otus.spring.hw11.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(String id);

    List<Genre> getAll();
}
