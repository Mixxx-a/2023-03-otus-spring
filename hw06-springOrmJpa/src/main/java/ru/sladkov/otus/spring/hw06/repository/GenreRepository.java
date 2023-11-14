package ru.sladkov.otus.spring.hw06.repository;

import ru.sladkov.otus.spring.hw06.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    List<Genre> findAll();

    Optional<Genre> findById(long id);
}
