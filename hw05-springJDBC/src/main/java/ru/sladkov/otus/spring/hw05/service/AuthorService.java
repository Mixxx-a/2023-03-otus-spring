package ru.sladkov.otus.spring.hw05.service;

import ru.sladkov.otus.spring.hw05.domain.Author;
import ru.sladkov.otus.spring.hw05.exception.NotFoundException;

import java.util.List;

public interface AuthorService {
    Author getAuthorById(long id) throws NotFoundException;

    List<Author> getAllAuthors();
}
