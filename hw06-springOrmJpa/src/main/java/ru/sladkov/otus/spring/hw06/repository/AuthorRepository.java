package ru.sladkov.otus.spring.hw06.repository;

import ru.sladkov.otus.spring.hw06.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> findAll();

    Optional<Author> findById(long id);
}
