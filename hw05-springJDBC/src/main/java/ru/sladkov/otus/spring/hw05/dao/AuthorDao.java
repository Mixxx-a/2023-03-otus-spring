package ru.sladkov.otus.spring.hw05.dao;

import ru.sladkov.otus.spring.hw05.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    List<Author> getAll();

    Optional<Author> getById(long id);
}
