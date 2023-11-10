package ru.sladkov.otus.spring.hw05.dao;

import ru.sladkov.otus.spring.hw05.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    List<Genre> getAll();

    Optional<Genre> getById(long id);
}
