package ru.sladkov.otus.spring.hw07.repository;

import ru.sladkov.otus.spring.hw07.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> findAll();

    Optional<Author> findById(long id);
}
