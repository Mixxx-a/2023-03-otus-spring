package ru.sladkov.otus.spring.hw07.service;

import ru.sladkov.otus.spring.hw07.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getById(long id);

    List<Author> getAll();
}
